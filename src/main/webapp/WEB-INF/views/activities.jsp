<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Activities</title>
	</head>
	<body>
	<ul>
		<li><a href="<c:url value="/" />">Home</a></li>
	</ul>
	
	<h3>Activity added</h3>
	<table>
		<tr>
		    <td>id</td><td><c:out value="${activity.id}"/></td>
		</tr>
	</table>

	</body>
</html>