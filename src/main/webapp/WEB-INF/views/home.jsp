<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Home</title>
	</head>
	<body>
	<ul>
		<li><a href="<c:url value="/signout" />">Sign Out</a></li>
	</ul>
	
	<c:url value="/people" var="people_url"/>
	<c:url value="/photos" var="photos_url"/>
	<c:url value="/activities" var="activities_url"/>
	<ul>
		<li><a href="${people_url}"/>People</a></li>
		<li><a href="${photos_url}"/>Photos</a></li>
		<li><a href="${activities_url}"/>Activities</a></li>
	</ul>	
	</body>
</html>