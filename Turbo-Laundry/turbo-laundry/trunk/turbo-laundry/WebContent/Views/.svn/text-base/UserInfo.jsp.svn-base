<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset="UTF-8">
<title>My account</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Users/User.css" />
</head>
<body>

	<div id="welcome" class="center"> <p>User information<p></div>
	<c:if test="${sessionScope.isAdmin == true }">
			    	
	</c:if>
	
	
	
	<div id="user-info" class="center">
	
		<c:choose>
		<c:when test="${isAdmin}">
			<div id = "user-menu" class="center">
		  			<a href="Edit?userReq=${user.getLogin()}" style="margin-bottom: 10px">Edit user</a>
			</div>	

			<div class="">
				<fieldset>
				<legend>Charge Account</legend>
					<form action="ChargeAccount" method="post" >
					 	<table>
					 		<tr><td>Charge:</td><td><input name="charge" type="text"/></td></tr>
							<tr><td>Current money:</td><td><c:out value ="${user.getAccount().getMoney()}"/></td></tr>
					 	</table>
					 	<input name="id" type ="hidden" value="${user.getId()}"/>
					 	<input type ="submit"/>
					 </form>
				</fieldset>	
			</div> 	
		</c:when>
	</c:choose>
	<fieldset>
	<legend>Basic information</legend>
		 	<table>
				<tr><td>Login:</td>
				<td><c:out value = "${user.getLogin()}"></c:out></td></tr>
				<tr><td>First name:</td>
				<td><c:out value="${user.getFirstName()}" /></td></tr>
				<tr><td>Last name:</td>
				<td><c:out value ="${user.getLastName()}"/></td></tr>
				<tr><td>Floor number:</td>
			 	<td><c:out value ="${user.getRoom().getFloor().getFloorNumber()}"/></td></tr>
				<tr><td>Room number:</td>
			 	<td><c:out value ="${user.getRoom().getNumber()}"/></td></tr>
			 	<tr><td>Room window account:</td>
			 	<td><c:out value ="${user.getRoom().getWindowsCount()}"/></td></tr>
		 	</table>
	</fieldset>	 	


	<fieldset class="change-password">
	<legend>Password</legend>
		<dl>
			<s:actionerror />
			<s:form action="ChangePassword" method="post" >
				<input name="user" type ="hidden" value="${user.getLogin()}"/>
				<s:password name="newPassword" label="New password" />
				<s:password name="confirmedPassword" label="Confirm new password" />
				<s:submit />
			</s:form>
		</dl>
	</fieldset>
	<fieldset class="account-info">
	<legend>Washing</legend>
		<table>
			<tr><td>Money:
			</td><td><c:out value ="${user.getAccount().getMoney()}"/></td></tr>
			
		</table>
	</fieldset>
</div>
	<br>
	<div id="welcome" class="center"> <p>Report<p></div>
	<div id="report" class="center">
		<label for="fromstr">From</label>
		<input type="text" id="fromstr" name="fromstr" />
		<label for="tostr">To</label>
		<input type="text" id="tostr" name="tostr" />
		<div id="report-form">
			<form action="GenerateReport" method="post">
			
				<input type="hidden" name="fromstr"  />
				<input type="hidden" name="tostr"  />
				<input type="submit" class="submitReport"/>
			</form>
		</div>
	</div>

</body>
</html>