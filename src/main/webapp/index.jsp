<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home page</title>
		<script type="text/javascript" src='<c:url value="JS/main.js"/>'></script>
		<link rel="stylesheet" href='<c:url value="css/main.css"/>'>
	</head>
	<body>
		
		<c:if test="${not empty user}">
			${user.login}
			<a href="<c:url value="javascript:sendData('conf_by_user')"/>">Account</a>
			<a href='<c:url value = "/logout"/>'>Logout</a>
		</c:if>
		<br>
		<c:if test="${empty user}">
			<a href='<c:url value = "/Login.jsp"/>'>LogIn</a>
			<a href='<c:url value= "/Registr.jsp"/>'>Registration</a>
		</c:if>
		
		<c:if test="${not empty message}">
			<c:out value="${message}"></c:out>
		</c:if>
		
		<table>
			<tr>
				<th><a href="javascript:sendData('all')">All</a></th>
				<th><a href="javascript:sendData('today')">Today</a></th>
				<th><a href="javascript:sendData('tomorrow')">Tomorrow</a></th>
				<th><a href="javascript:sendData('soon')">Soon</a></th>
				<th><a href="javascript:sendData('past')">Past</a></th>
			</tr>
		</table>
		<jsp:include page="/viewConfList.jsp"/>
	</body>
</html>