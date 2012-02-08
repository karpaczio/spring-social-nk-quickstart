	<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Photos</title>
	</head>
	<body>
	<ul>
		<li><a href="<c:url value="/" />">Home</a></li>
	</ul>
	
	<h3>Current User Albums</h3>
	<table>
	<c:if test="${not empty currentUserAlbums.entry}">
		<c:forEach items="${currentUserAlbums.entry}" var="album">
			<tr>
				<td colspan="2"><strong><c:out value="${album.title}"/></strong></td>
			</tr><tr>
				<td>thumbnailUrl</td><td><c:out value="${album.thumbnailUrl}"/></td>
			</tr><tr>
				<td>description</td><td><c:out value="${album.description}"/></td>
			</tr><tr>
				<td>id</td><td><c:out value="${album.id}"/></td>
			</tr><tr>
				<td>mediaItemCount</td><td><c:out value="${album.mediaItemCount}"/></td>
			</tr><tr>
				<td>mediaMimeType</td><td><c:out value="${album.mediaMimeType}"/></td>
			</tr><tr>
				<td>mediaType</td><td><c:out value="${album.mediaType}"/></td>
			</tr><tr>
				<td>ownerId</td><td><c:out value="${album.ownerId}"/></td>
			</tr><tr>
				<td>
					<c:url value="/photos" var="add_photo_url">
						<c:param name="albumId" value="${album.id}"/>
						<c:param name="description" value="some description"/>
					</c:url>
					<form action="${add_photo_url}" method="POST">
						<button type="submit">Add photo</button>
					</form> 
				</td>
			</tr>
		</c:forEach>
	</c:if>
	</table>	
	
	
	<h3>Group Albums</h3>
	<table>
	<c:if test="${not empty groupAlbums.entry}">
		<c:forEach items="${groupAlbums.entry}" var="album">
			<tr>
				<td colspan="2"><strong><c:out value="${album.title}"/></strong></td>
			</tr><tr>
				<td>thumbnailUrl</td><td><c:out value="${album.thumbnailUrl}"/><img src="${album.thumbnailUrl}"/></td>
			</tr><tr>
				<td>description</td><td><c:out value="${album.description}"/></td>
			</tr><tr>
				<td>id</td><td><c:out value="${album.id}"/></td>
			</tr><tr>
				<td>mediaItemCount</td><td><c:out value="${album.mediaItemCount}"/></td>
			</tr><tr>
				<td>mediaMimeType</td><td><c:out value="${album.mediaMimeType}"/></td>
			</tr><tr>
				<td>mediaType</td><td><c:out value="${album.mediaType}"/></td>
			</tr><tr>
				<td>ownerId</td><td><c:out value="${album.ownerId}"/></td>
			</tr>
		</c:forEach>
	</c:if>
	</table>
	</body>
</html>