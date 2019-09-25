<%@page import="by.itClass.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration page</title>
</head>
<body>
	<h2>Registration form</h2>
	<form action='<c:url value="/regist"/>' method="post">
		<input type="text" name="<%=Constants.LOGIN %>" placeholder="Login">
		<input type="password" name="<%=Constants.PASSWORD %>" placeholder="Password">
		<input type="text" name="<%=Constants.FIRST_NAME %>" placeholder="First name">
		<input type="text" name="<%=Constants.SECOND_NAME %>" placeholder="Second name">
		<input type="text" name="<%=Constants.EMAIL %>" placeholder="Email">
		<input type="submit" value="Registration">
	</form>
	<c:if test="${not empty message}">
			<c:out value="${message}"></c:out>
		</c:if>
</body>
</html>