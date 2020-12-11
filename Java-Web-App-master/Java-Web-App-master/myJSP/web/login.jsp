<%-- 
    Document   : login
    Created on : Aug 25, 2018, 10:24:38 PM
    Author     : Kayee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%@ include file="navbar.jsp"%>
        <% if (session.getAttribute("message") != null) {%>
           <h1 class="message"><%=session.getAttribute("message")%></h1>
        <%}%>
        <div class="login-container">
        <form action="loginServlet" method="post" class="login-form">
            <h1>Login</h1>
            <input type="text" name="username" placeholder="Enter Username" required>
            <br>
            <input type="password" name="password" placeholder="Enter Password" required> 
            <br>
            <input type="submit" value="Login">
        </form> 
        </div>
    </body>
</html>

