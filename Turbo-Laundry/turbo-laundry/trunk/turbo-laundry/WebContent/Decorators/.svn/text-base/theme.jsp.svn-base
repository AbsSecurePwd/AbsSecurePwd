<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Users/User.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar/laundry_calendar.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar/laundry_day.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/smoothness/jquery-ui-1.9.2.custom.css" />
	
    <link href='http://fonts.googleapis.com/css?family=Chango' rel='stylesheet' type='text/css'/>
	<link href='http://fonts.googleapis.com/css?family=Galindo' rel='stylesheet' type='text/css'/>	
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.8.3.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/showMessage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/calendar.js"></script>
	
	
</head>
<body>
    <div id="header">
    	
    	<h1><img src="${pageContext.request.contextPath}/img/whirl.png" width="50px"/>Turbo Laundry</h1>
    	<c:if test="${not empty sessionScope.authorized }">
	    <div id="navigation">
		  	<ul>
		  		<li> <a href="${pageContext.request.contextPath}/Rules">Rules</a></li>
		    	<li> <a href="${pageContext.request.contextPath}/ACalendar">Calendar</a>	</li>
		    	<c:if test="${not sessionScope.isAdmin == true }">
		    		<li> <a href="${pageContext.request.contextPath}/UserInfo">Profile</a>	</li>
		    	
		    	</c:if>		    	
		    	<c:if test="${sessionScope.isSuper == true }">
		    		<li><a href="${pageContext.request.contextPath}/ListUsers?floor=1">List Users</a></li>
		    	
		    	</c:if>
		    	<c:if test="${sessionScope.isAdmin == true }">
		    		<li><a href="${pageContext.request.contextPath}/AdminListUsers?floor=1">List Users</a></li>
		    	
		    	</c:if>
		    	<c:if test="${sessionScope.isAdmin == true }">
		    		<li><a href="${pageContext.request.contextPath}/Create">Create User</a></li>
		    	
		    	</c:if>	
		    	<li> <a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
				
		  	</ul>
		</div>
	</c:if>
    </div>
   
    <decorator:body />
    
    
    
    <div id="footer">
   		 <p>Copyright  Turbo Laundry Team 2013</p>
    </div>
  
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/checkCredentials.js"></script>
</body>
</html>