<%@page import="by.itClass.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>Login page</title>
	</head>
	<body>
		<h2>Login form</h2>
		<form action='<c:url value="/login"/>' method="post">
			<input type="text" name="<%= Constants.LOGIN%>" value="" placeholder="Login">
			<input type="password" name="<%= Constants.PASSWORD%>" value="" placeholder="Password">
			<input type="submit" value="Login">
		</form>
		
		<c:if test="${not empty message}">
			<c:out value="${message}"></c:out>
		</c:if>
	</body>
</html>