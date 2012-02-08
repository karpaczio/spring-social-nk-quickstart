	<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>People</title>
	</head>
	<body>
	<ul>
		<li><a href="<c:url value="/" />">Home</a></li>
	</ul>
	
	<h3>Current user</h3>
	<img src="${currentUser.thumbnailUrl}"/> <c:out value="${currentUser.displayName}"/>
	<table>
		<tr>
		    <td>id                     </td><td><c:out value="${currentUser.id}"/></td>
		</tr>
		<tr>                                                                                                                                                                      
		    <td>age                    </td><td><c:out value="${currentUser.age}"/></td>
		</tr>
		<tr>                                                                                                                                                                     
		    <td>currentLocation.region </td><td><c:out value="${currentUser.currentLocation.region}"/></td>
		</tr>
		<tr>
		    <td>displayName            </td><td><c:out value="${currentUser.displayName}"/></td>
		</tr>
		<tr>                                                                                                                                                             
		    <td>nkFriendsCount         </td><td><c:out value="${currentUser.nkFriendsCount}"/></td>
		</tr>
		<tr>        
		    <td>gender                 </td><td><c:out value="${currentUser.gender}"/></td>
		</tr>
		<tr>                                                                                                                                                                  
		    <td>hasApp                 </td><td><c:out value="${currentUser.hasApp}"/></td>
		</tr>
		<tr>                                                                                                                                                                  
		    <td>name.formatted         </td><td><c:out value="${currentUser.name.formatted}"/></td>
		</tr>
		<tr>        
		    <td>name.additionalName    </td><td><c:out value="${currentUser.name.additionalName}"/></td>
		</tr>
		<tr>      
		    <td>name.familyName        </td><td><c:out value="${currentUser.name.familyName}"/></td>       
		</tr>
		<tr>
		    <td>name.givenName         </td><td><c:out value="${currentUser.name.givenName}"/></td>        
		</tr>
		<tr>
		    <td>phoneNumbers           </td><td><table><c:forEach items="${currentUser.phoneNumbers}" var="phone"><tr><td><c:out value="${phone.type}"/></td><td><c:out value="${phone.value}"/></td></c:forEach></table></td>          
		</tr>
		<tr>
		    <td>photos                 </td><td><table><c:forEach items="${currentUser.photos}" var="photo"><tr><td><c:out value="${photo.type}"/></td><td><c:out value="${photo.value}"/></td></c:forEach></table></td>                                                                                                                                                                  
		</tr>
		<tr>
		    <td>profileUrl             </td><td><c:out value="${currentUser.profileUrl}"/></td>                                                                                                                                                              
		</tr>
		<tr>
		    <td>thumbnailUrl           </td><td><c:out value="${currentUser.thumbnailUrl}"/></td>                                                                                                                                                            
		</tr>
		<tr>
		    <td>urls                   </td><td><table><c:forEach items="${currentUser.urls}" var="url"><tr><td><c:out value="${url.type}"/></td><td><c:out value="${url.value}"/></td></c:forEach></table></td>                                                                                                                                                                    		
		 </tr>
	</table>
	
	<h3>Current user friends</h3>
	<ul>
	<c:if test="${not empty currentUserFriends.entry}">
		<c:forEach items="${currentUserFriends.entry}" var="friend">
			<li><img src="${friend.thumbnailUrl}"/><c:out value="${friend.displayName}"/></li>
		</c:forEach>
	</c:if>
	</ul>	
	
	<h3>Given user</h3>
	<img src="${givenUser.thumbnailUrl}"/> <c:out value="${givenUser.displayName}"/>
	<table>
		<tr>
		    <td>id                     </td><td><c:out value="${givenUser.id}"/></td>                                                                                                                                                                      
		</tr>
		<tr>
		    <td>age                    </td><td><c:out value="${givenUser.age}"/></td>                                                                                                                                                                     
		</tr>
		<tr>
		    <td>currentLocation.region </td><td><c:out value="${givenUser.currentLocation.region}"/></td>
		</tr>
		<tr>
		    <td>displayName            </td><td><c:out value="${givenUser.displayName}"/></td>                                                                                                                                                             
		</tr>
		<tr>
		    <td>nkFriendsCount         </td><td><c:out value="${givenUser.nkFriendsCount}"/></td>        
		</tr>
		<tr>
		    <td>gender                 </td><td><c:out value="${givenUser.gender}"/></td>                                                                                                                                                                  
		</tr>
		<tr>
		    <td>hasApp                 </td><td><c:out value="${givenUser.hasApp}"/></td>                                                                                                                                                                  
		</tr>
		<tr>
		    <td>name.formatted         </td><td><c:out value="${givenUser.name.formatted}"/></td>        
		</tr>
		<tr>
		    <td>name.additionalName    </td><td><c:out value="${givenUser.name.additionalName}"/></td>   
		</tr>
		<tr>
		    <td>name.familyName        </td><td><c:out value="${givenUser.name.familyName}"/></td>       
		</tr>
		<tr>
		    <td>name.givenName         </td><td><c:out value="${givenUser.name.givenName}"/></td>        
		</tr>
		<tr>
		    <td>phoneNumbers           </td><td><table><c:forEach items="${givenUser.phoneNumbers}" var="phone"><tr><td><c:out value="${phone.type}"/></td><td><c:out value="${phone.value}"/></td></c:forEach></table></td>          
		</tr>
		<tr>
		    <td>photos                 </td><td><table><c:forEach items="${givenUser.photos}" var="photo"><tr><td><c:out value="${photo.type}"/></td><td><c:out value="${photo.value}"/></td></c:forEach></table></td>                                                                                                                                                                  
		</tr>
		<tr>
		    <td>profileUrl             </td><td><c:out value="${givenUser.profileUrl}"/></td>                                                                                                                                                              
		</tr>
		<tr>
		    <td>thumbnailUrl           </td><td><c:out value="${givenUser.thumbnailUrl}"/></td>                                                                                                                                                            
		</tr>
		<tr>
		    <td>urls                   </td><td><table><c:forEach items="${givenUser.urls}" var="url"><tr><td><c:out value="${url.type}"/></td><td><c:out value="${url.value}"/></td></c:forEach></table></td>                                                                                                                                                                    		
		 </tr>
	</table>
	
	<h3>Given users:</h3>
	<ul>
	<c:if test="${not empty givenUsers.entry}">
		<c:forEach items="${givenUsers.entry}" var="user">
			<li><img src="${user.thumbnailUrl}"/><c:out value="${user.displayName}"/></li>
		</c:forEach>
	</c:if>
	</ul>
	</body>
</html>