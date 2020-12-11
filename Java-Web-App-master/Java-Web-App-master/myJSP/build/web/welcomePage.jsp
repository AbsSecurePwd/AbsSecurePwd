<%-- 
    Document   : home
    Created on : Aug 25, 2018, 9:27:18 PM
    Author     : Kayee
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% if (session.getAttribute("username") == null) { %>
    <jsp:forward page="login.jsp" />
<% } %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body> 
        <%@ include file="navbar.jsp"%>
        <!--
        <nav>
            <a href="welcomePage.jsp">Home</a> |
            <a href="userTable.jsp">User Table</a> |
            <a href="assignWork.jsp">Assign Work</a> |
            <a href="logout.jsp">Logout</a>
        </nav>
        -->
        <div class="welcomePage-contaniner">
        <h1>Welcome <%=session.getAttribute("username")%>!</h1>
        <h3><u>Account Details: </u></h3>
        <h4>Username: <%=session.getAttribute("username")%></h4>
        <h4>Account type: <%=session.getAttribute("user_type")%></h4>
        </div>
    </body>
</html>
