<%@page import="by.itClass.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script type="text/javascript" src='<c:url value="JS/main.js"/>'></script>
		<title>Add new conference:</title>
	</head>
	<body>
	<c:if test="${not empty message}">
			<c:out value="${message}"></c:out>
		</c:if>
		<h2>Conference</h2>
		<form action='<c:url value="/addConf"/>' method="post" id="addConf">
			<input type="text" name="<%= Constants.TITLE %>" placeholder="Title"><br>
			<input type="text" name="<%= Constants.DESCR %>" placeholder="Description"><br>
			<input type="text" name="<%= Constants.PLACE %>" placeholder="Place"><br>
			<input type="date" name="<%= Constants.DATE %>" placeholder="Date"><br>
			<p>Events</p>
			<div id="event">
				<input type="text" name="<%= Constants.TITLE_EVENT %>" placeholder="Title" required>
				<input type="time" name="<%= Constants.TIME %>" placeholder="Time" required>
				<br>
			</div>
		</form>
		<a href="javaScript:addEvent()">Add event</a><br>
		<input type="submit" value="Create" form="addConf">
	</body>
</html>