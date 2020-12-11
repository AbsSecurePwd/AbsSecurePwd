<%-- 
    Document   : logout
    Created on : Aug 27, 2018, 12:35:35 PM
    Author     : Kayee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
    </head>
    <body>
        <%@ include file="navbar.jsp"%>
        <%
            response.setHeader("Pragma","no cache");
            response.setHeader("Expires", "0");
            response.setDateHeader("Expires", -1);
            session.invalidate();
        %>
        <h2 style="padding-left: 10%">You have successfully logged out.</h2>
    </body>
</html>
