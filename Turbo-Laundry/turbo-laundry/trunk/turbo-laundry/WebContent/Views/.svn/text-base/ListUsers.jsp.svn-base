<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--<%@page import="java.util.Date"%>-->

<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset="UTF-8">
<title>User list</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Users/User.css" />
</head>
<body>

	<div id="welcome" class="center"> <p>User list<p></div>
	<div id="users" class="center">
		<table class="users" id="tusers">
			<tr>
				<th>Login</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Room</th>
				<th>Block</th>
			</tr>
			<c:forEach begin="0" end="${users.size() - 1}" step="1" varStatus="i">
				<tr>
					<td><a href="UserInfo?userReq=${users.get(i.index).getLogin()}"> ${users.get(i.index).getLogin()} </a></td>
					<td>${users.get(i.index).getFirstName()}</td>
					<td>${users.get(i.index).getLastName()}</td>
					<td>${users.get(i.index).getRoom(). getNumber()}</td>
					<td>
						<c:choose>
						<c:when test="${users.get(i.index).getBlocked() == null}">
							<a href="Block?block=true&id=${users.get(i.index).getId()}" > Block </a>
						</c:when>
						<c:otherwise>
							<a href="Block?block=false&id=${users.get(i.index).getId()}" class="blocked" > Unblock </a>
						</c:otherwise>
	
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>


