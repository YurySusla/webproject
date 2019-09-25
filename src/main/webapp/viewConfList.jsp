<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
   <c:if test="${not empty conferences}">
			<table style="border: 1px solid">
				<tr>
					<th>Conference</th>
					<th>Place</th>
					<th>Date</th>
				</tr>
				<c:forEach var="conf" items = "${conferences}">
					<tr>
						<td id="idConf">${conf.id}</td>
						<td>
							<a href="javascript:getConf('${conf.id}')">${conf.title}</a>
							<p>${conf.descr}</p>
						</td>
						<td>${conf.place}</td>
						<td>${conf.date}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<form name="viewConfForm" action="<c:url value="/ViewConf"/>" method="get">
			<input name="idConf" type="hidden">
		</form>
