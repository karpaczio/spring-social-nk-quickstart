package org.springframework.social.nk.quickstart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.shindig.protocol.RestfulCollection;
import org.springframework.http.HttpStatus;
import org.springframework.social.nk.api.Nk;
import org.springframework.social.nk.api.PeopleOperations;
import org.springframework.social.nk.api.impl.PeopleTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;

import pl.nk.opensocial.model.NkPerson;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final Nk nk;

    @Inject
    public PeopleController(Nk nk) {
        this.nk = nk;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model) {

        PeopleOperations<PeopleTemplate> oper = this.nk.peopleOperations();
        NkPerson currentUser = oper.getCurrentUserProfile();
        RestfulCollection<NkPerson> currentUserFriends = getCurrentUserFriends();
        NkPerson givenUser = null;
        RestfulCollection<NkPerson> givenUsers = new RestfulCollection<NkPerson>(Collections.<NkPerson>emptyList());

        if (currentUserFriends != null && !currentUserFriends.getEntry().isEmpty()) {
            String personId = currentUserFriends.getEntry().get(0).getId();
            
            givenUser = getUserProfile(personId);

            List<String> personIds = new ArrayList<String>();
            for (NkPerson p : currentUserFriends.getEntry()) {
                personIds.add(p.getId());
            }
            givenUsers  = getUserProfiles(personIds);

        }

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentUserFriends", currentUserFriends);
        model.addAttribute("givenUser", givenUser);
        model.addAttribute("givenUsers", givenUsers);

        return "people";
    }
    
    private  RestfulCollection<NkPerson> getCurrentUserFriends() {
        try {
            return this.nk.peopleOperations().getCurrentUserFriends();
        } catch (HttpClientErrorException error) {
            if (HttpStatus.UNAUTHORIZED.equals(error.getStatusCode())) {
                return null;
            } else {
                throw error;
            }
        }
    }
    
    private NkPerson getUserProfile(String personId) {
        try {
            return this.nk.peopleOperations().getUserProfile(personId);
        } catch (HttpClientErrorException error) {
            if (HttpStatus.UNAUTHORIZED.equals(error.getStatusCode())) {
                return null; 
            } else {
                throw error;
            }
        }
    }

    private RestfulCollection<NkPerson> getUserProfiles(List<String> personIds) {
        try {
            return this.nk.peopleOperations().getUsersProfiles(personIds);
        } catch (HttpClientErrorException error) {
            if (HttpStatus.UNAUTHORIZED.equals(error.getStatusCode())) {
                return null;
            } else {
                throw error;
            }
        }
    }
}
