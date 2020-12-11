<%-- 
    Document   : order
    Created on : Aug 25, 2018, 9:42:43 PM
    Author     : Kayee
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="EJB.db_con"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% if (session.getAttribute("username") == null) { %>
    <jsp:forward page="login.jsp" />
<% }%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%@ include file="navbar.jsp"%>
        <% if (session.getAttribute("message") != null) {%>
           <h1 class="message"><%=session.getAttribute("message")%></h1>
        <%}%>
        
        <%
        String user_type = session.getAttribute("user_type").toString();
        if(user_type.equals("user")) { %>
            <div class="booking-container">
            <form action="addBookingServlet" method="post" class="booking-form">
            <h3>Booking:</h3>
            <% 
                String booking_name = session.getAttribute("username").toString(); 
                out.println("<input type=\"hidden\" name=\"bk_name\" value=\"" + booking_name + "\">");
            %>
            
            <input type="date" name="date" required><br>
            <input type="submit" value="Make a booking">
            </form>
            </div>
        <%}%>
        
        <div class="table-container">
        <%
        
        db_con db_con = new db_con();
        Connection con = db_con.getCon();
        String sql = "";

        try {
            Statement statement = con.createStatement();
            
            sql = "SELECT * FROM BOOKING WHERE BK_STATUS = 'Pending'";
            ResultSet resultSet = statement.executeQuery(sql); //execute sql
            
            //Display pending booking table
            out.println("<h3>Current Bookings:</h3>"
                + "<form action=\"cancelServlet\" method=\"post\"><table>"
                + "<tr><th>Booking ID</th><th>Booking Name</th>"
                + "<th>Booking Date</th><th>Booking Status</th><th></th></tr>");
            
            while(resultSet.next()) {
                String bk_id = resultSet.getString("BK_ID");
                String bk_name = resultSet.getString("BK_NAME");
                String bk_date = resultSet.getString("BK_DATE");
                String bk_status = resultSet.getString("BK_STATUS");

                out.println("<tr><td>" + bk_id + "</td><td>" + bk_name + "</td>"
                    + "<td>" + bk_date + "</td><td>" + bk_status + "</td><td>");
                    
                String username = session.getAttribute("username").toString();
                if(username.equals(bk_name)) {
                    out.print("<input type=\"hidden\" name=\"hidden\" value=\""+bk_id+"\">"
                        + "<input type=\"submit\" value=\"Cancel\">");
                }
                out.println("</td></tr>"); 
            }

            out.println("</table></form>");
            
            //Display all booking table
            sql = "SELECT * FROM BOOKING";
            resultSet = statement.executeQuery(sql);
            
            out.println("<h3>All Bookings:</h3>"
                + "<table>"
                + "<tr><th>Booking ID</th><th>Booking Name</th>"
                + "<th>Booking Date</th><th>Booking Status</th></tr>");
            while(resultSet.next()) {
                String bk_id = resultSet.getString("BK_ID");
                String bk_name = resultSet.getString("BK_NAME");
                String bk_date = resultSet.getString("BK_DATE");
                String bk_status = resultSet.getString("BK_STATUS");

                out.println("<tr><td>" + bk_id + "</td><td>" + bk_name + "</td>"
                    + "<td>" + bk_date + "</td><td>" + bk_status + "</td></tr>");
                
            }
            out.println("</table><br><br>");
            statement.close();
            con.close();

        } catch (Exception ex) {
            out.println(ex);
        }
        %>
        </div>
        <br><br>
    </body>
</html>
