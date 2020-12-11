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
		<div class="floors">
			<br/>
			<h4>User of floor ${floor}</h4>

			<c:forEach var="item" items="${floors}">	
				<a href="AdminListUsers?floor=${item.getFloorNumber()}">${item.getFloorNumber()}</a>
			</c:forEach>
		</div>
		
		<div>
			<br/>
			<b>Supervisor:</b>
			<c:choose>
				<c:when test="${sv != null}" >
					<a href="UserInfo?userReq=${users.get(i.index).getLogin()}"> ${sv.getUser().getFirstName()} ${sv.getUser().getLastName()}</a>
			 	</c:when>
			 </c:choose>
			<br/>
		</div>
		<table class="users" id="tusers">
			<tr>
				<th>Login</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Room</th>
				<th>Account</th>
				<th>Supervisor</th>
			</tr>
			<c:choose>
				<c:when test="${users.size()>0}">
					<c:forEach begin="0" end="${users.size() - 1}" step="1" varStatus="i">
						<tr>
							<td><a href="UserInfo?userReq=${users.get(i.index).getLogin()}"> ${users.get(i.index).getLogin()} </a></td>
							<td>${users.get(i.index).getFirstName()}</td>
							<td>${users.get(i.index).getLastName()}</td>
							<td>${users.get(i.index).getRoom().getNumber()}</td>
							<td>${users.get(i.index).getAccount().getMoney()}</td>
							<td><a href="MakeSupervisor?id=${users.get(i.index).getId()}&floor=${floor}">Make supervisor</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
				<tr> There is no user on the floor ${floor}. </tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
</body>
</html>


