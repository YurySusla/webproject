<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Conference info</title>
	</head>
	<body>
		<c:set var="conf" value="${conferences[indexConf]}" scope="page"/>
		<h1>${conf.title}</h1>
		
		<p>Place: ${conf.place}</p>
		<p>Date: ${conf.date}</p>
		
		<h2>Conference program</h2>
		
		
			<table style="border:1px solid">
				<tr>
					<th>id</th>
					<th>Event</th>
					<th>Time</th>
				</tr>
				<c:forEach var="event" items="${conf.events}">
					<tr>
						<td>
						<c:out value="${event.id}"></c:out></td>
						<td>
						<c:out value="${event.title}"></c:out></td>
						<td>
						<c:out value="${event.time}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
	</body>
</html>