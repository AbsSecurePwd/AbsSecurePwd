<%-- 
    Document   : userlist
    Created on : Aug 27, 2018, 9:06:51 AM
    Author     : Kayee
--%>

<%@page import="javax.ejb.EJB"%>
<%@page import="EJB.db_con"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% if (session.getAttribute("username") == null) { %>
    <jsp:forward page="login.jsp" />
<% } %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Table Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%@ include file="navbar.jsp"%>
        
        <!-- Search bar -->
        <form class="searchbar" action="userTable.jsp">
          <input type="text" placeholder="Search user by name..." name="searchbar">
          <button type="submit">Search</button>
        </form>
        
        <div class="table-container">
        <%
        
        db_con db_con = new db_con();
        Connection connection = db_con.getCon();
        String sql = "";
        String search_keyword = request.getParameter("searchbar");

        try {
            Statement statement = connection.createStatement();
            
            if (search_keyword != null) { // display filtered table
                sql = "SELECT * FROM USERS WHERE USER_NAME like '%"
                        + search_keyword + "%' ORDER BY USER_ID ASC";
                
            } else { // display whole table
                sql = "SELECT * FROM USERS";
            }
            
            ResultSet resultSet = statement.executeQuery(sql); //execute sql
            
            //output user table
            out.println("<h3>User Table:</h3>"
                    + "<table>"
                    + "<tr><th>ID</th><th>Name</th><th>Email</th>"
                    + "<th>Password</th><th>Account Type</th></tr>");
            
            while(resultSet.next()) {
                String id = resultSet.getString("USER_ID");
                String name = resultSet.getString("USER_NAME");
                String email = resultSet.getString("USER_EMAIL");
                String password = resultSet.getString("USER_PASSWORD");
                String type = resultSet.getString("USER_TYPE");

                out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>"
                        + email + "</td><td>" + password + "</td><td>" + type + "</td></tr>");
            }

            out.println("</table>");
                 statement.close();
            connection.close();

        } catch (SQLException ex) {
            out.println(ex);
        }
        %>
        </div>
    </body>
</html>
