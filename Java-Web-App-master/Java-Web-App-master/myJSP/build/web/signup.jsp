<%-- 
    Document   : index
    Created on : Aug 25, 2018, 3:28:21 PM
    Author     : Kayee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%@ include file="navbar.jsp"%>
        <br>
        <div class="signup-container">
        <form action="signupServlet" method="post" class="signup-form">
            <h1>Sign Up</h1>
            <p>Please fill in this form to create an account.</p>
            <input type="text" id="username" name="username" placeholder="Enter Username" required>
            <br>
            <input type="email" id="email" name="email" placeholder="Enter Email" required>
            <br>
            <input type="password" id="password" name="password" placeholder="Enter Password" required> 
            <br>
            <input type="submit" value="Signup">
        </form> 
        </div>
    </body>
</html>
