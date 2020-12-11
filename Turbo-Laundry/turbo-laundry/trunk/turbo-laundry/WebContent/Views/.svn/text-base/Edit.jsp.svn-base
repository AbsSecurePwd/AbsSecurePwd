<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset="UTF-8">
<title>Edit user</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Users/User.css" />
</head>
<body>

	<div id="welcome" class="center"> <p>Edit user ${user.getLogin()}<p></div>
	
	<div id="user-info" class="center">
			<form action="InsertOrUpdateUser" method="post">
				<input type="hidden" name="id" value="${user.getId()}"/>
				<input type="hidden" name="Login" value="${user.getLogin()}"/>
				<table>
				<tr><td>Login:</td>
				<td><input name="userLogin" value="${user.getLogin()}" disabled="disabled"/> </td></tr>
				<tr><td>First name:</td>
				<td><input  name="firstName" value="${user.getFirstName()}"/></td></tr>
				<tr><td>Last name:</td>
				<td><input  name="lastName" value="${user.getLastName()}"/></td></tr>
				<tr><td>Room number:</td>
			 	<td>
			 		<select name="room">
				        <c:forEach var="item" items="${rooms}">
				        	<c:choose>
				        	<c:when test="${item.getId() == user.getRoom().getId()}">
				        		<option value="${item.getId()}"  selected = "selected">floor:${item.getFloor().getFloorNumber()} room: ${item.getNumber()}</option>
				        	</c:when>
				        	<c:otherwise>
				        		<option value="${item.getId()}">floor:${item.getFloor().getFloorNumber()} room: ${item.getNumber()}</option>
				        		</c:otherwise>
				        	</c:choose>
				            
				        </c:forEach>
			    	</select>
			    </td> </tr>
			 	<tr><td>Room window account:</td>
			 	<td></td> </tr>
			 	</table>
			 	<input type="submit"/>
			 </form>	
	</div>

</body>
</html>