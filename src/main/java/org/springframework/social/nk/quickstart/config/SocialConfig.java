/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.nk.quickstart.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.oauth.common.signature.SharedConsumerSecret;
import org.springframework.security.oauth.common.signature.SignatureSecret;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.nk.api.Nk;
import org.springframework.social.nk.connect.NkConnectionFactory;
import org.springframework.social.nk.quickstart.test.TestTL;
import org.springframework.social.nk.quickstart.user.SecurityContext;
import org.springframework.social.nk.quickstart.user.SimpleConnectionSignUp;
import org.springframework.social.nk.quickstart.user.SimpleSignInAdapter;
import org.springframework.social.nk.quickstart.user.User;

/**
 * Spring Social Configuration.
 * @author Keith Donald
 */
@Configuration
public class SocialConfig {

	@Inject
	private Environment environment;

	@Inject
	private DataSource dataSource;

	/**
	 * When a new provider is added to the app, register its {@link ConnectionFactory} here.
	 * @see NkConnectionFactory
	 */
	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		BaseProtectedResourceDetails resource = new BaseProtectedResourceDetails();
		resource.setConsumerKey(environment.getProperty("nk.clientKey"));
		SignatureSecret secret = new SharedConsumerSecret(environment.getProperty("nk.clientSecret"));
		resource.setSharedSecret(secret);
		resource.setId("nk");
		
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new NkConnectionFactory(environment.getProperty("nk.clientKey"),
				environment.getProperty("nk.clientSecret"), resource));
		return registry;
	}
	
	// @Bean
	public OAuth2ProtectedResourceDetails nkResourceDetails() {
		BaseOAuth2ProtectedResourceDetails resource = new BaseOAuth2ProtectedResourceDetails();
		resource.setClientId(environment.getProperty("nk.clientKey"));
		resource.setClientSecret(environment.getProperty("nk.clientSecret"));
		resource.setId("nk");
		return resource;
	}

	/**
	 * Singleton data access object providing access to connections across all users.
	 */
	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator(), Encryptors.noOpText());
		repository.setConnectionSignUp(new SimpleConnectionSignUp());
		return repository;
	}

	/**
	 * Request-scoped data access object providing access to the current user's connections.
	 */
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
	    User user = SecurityContext.getCurrentUser();
	    return usersConnectionRepository().createConnectionRepository(user.getId());
	}

	/**
	 * A proxy to a request-scoped object representing the current user's primary Nk account.
	 * @throws NotConnectedException if the user is not connected to nk.
	 */
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
	public Nk nk() {
	    return connectionRepository().getPrimaryConnection(Nk.class).getApi();
	}
	
	/**
	 * The Spring MVC Controller that allows users to sign-in with their provider accounts.
	 */
	@Bean
	public ProviderSignInController providerSignInController() {
		return new ProviderSignInController(connectionFactoryLocator(), usersConnectionRepository(),
				new SimpleSignInAdapter());
	}
	
	@Bean
	public TestTL testTL() {
	    return new TestTL();
	}

}