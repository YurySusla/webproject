<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript" src='<c:url value="JS/main.js"/>'></script>
	<link rel="stylesheet" href='<c:url value="css/main.css"/>'>
		<meta charset="UTF-8">
		<title>Home page</title>
	</head>
		<body>
			<h2>Hello ${user.login}</h2>
			<p>Your name: ${user.firstName} ${user.secondName}</p>
			<p>Your email: ${user.email}</p>
			
			<h3>List conferences</h3>
			
			<jsp:include page="/viewConfList.jsp"/>
			<br>
			<br>
			<a href="<c:url value="/addConf.jsp"/>">New Conference</a>
			<a href="javascript:addCheckBox()">Delete Confernces</a>
		</body>
</html>