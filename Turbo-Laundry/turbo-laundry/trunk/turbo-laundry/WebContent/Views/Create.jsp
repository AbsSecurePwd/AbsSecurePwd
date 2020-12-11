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
<title>Create user</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Users/User.css" />
</head>
<body>

	<div id="welcome" class="center"> <p>Create User<p></div>
	<div id="user-info" class="center">
			<form action="InsertOrUpdateUser" method="post">
				<table>
				<tr><td>Login:</td>
				<td><input name="login"/> </td></tr>
				<tr><td>First name:</td>
				<td><input  name="firstName"/></td></tr>
				<tr><td>Last name:</td>
				<td><input  name="lastName"/></td></tr>	
				<tr><td>Room number:</td>
			 	<td>
			 		<select name="room">
				        <c:forEach var="item" items="${rooms}">
				            <option value="${item.getId()}">floor:${item.getFloor().getFloorNumber()} room: ${item.getNumber()}</option>
				        </c:forEach>
			    	</select>
			    
			    </td> 
			 	<tr><td>Room window account:</td>
			 	<td></td></tr> 
			 	</table>
			 	<input type="submit"/>
			 </form>	
	</div>

</body>
</html>