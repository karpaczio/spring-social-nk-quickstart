<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Sign In</title>
	</head>
	<body>
		<form action="<c:url value="/signin/nk" />" method="POST">
		    <button type="submit">Sign in with Nk</button>
		    <input type="hidden" name="scope" value="BASIC_PROFILE_ROLE,PERSON_FRIENDS_ROLE,PICTURES_PROFILE_ROLE,CREATE_SHOUTS_ROLE,CREATE_PHOTOS_ROLE" />		    
		</form>
	</body>
</html>
