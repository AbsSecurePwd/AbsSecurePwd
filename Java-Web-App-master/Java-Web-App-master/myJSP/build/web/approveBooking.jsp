<%-- 
    Document   : approve_booking
    Created on : Sep 19, 2018, 4:39:28 PM
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
        <title>Approve Booking</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%@ include file="navbar.jsp"%>
        <div class="table-container">
        <%
        int booking_counter = 0;
        db_con db_con = new db_con();
        Connection con = db_con.getCon();
        String sql = "";
        //String user = session.getAttribute("username").toString();

        try {
            Statement statement = con.createStatement();
            
            sql = "SELECT * FROM BOOKING WHERE BK_STATUS = 'Pending'";
            ResultSet resultSet = statement.executeQuery(sql); //execute sql
            
            //output booking table
            out.println("<h3>Current Bookings:</h3>"
                + "<form action=\"approveServlet\" method=\"post\"><table>"
                + "<tr><th>Booking ID</th><th>Booking Name</th>"
                + "<th>Booking Date</th><th>Booking Status</th><th></th</tr>");
            
            while(resultSet.next()) {
                String bk_id = resultSet.getString("BK_ID");
                String bk_name = resultSet.getString("BK_NAME");
                String bk_date = resultSet.getString("BK_DATE");
                String bk_status = resultSet.getString("BK_STATUS");

                out.println("<tr><td>" + bk_id + "</td><td>" + bk_name + "</td>"
                    + "<td>" + bk_date + "</td><td>" + bk_status + "</td>"
                    + "<td><input type=\"hidden\" name=\"hidden\" value=\""+bk_id+"\">"
                    + "<input type=\"submit\" value=\"Approve\"></td></tr>");
                
                booking_counter++;
            }
            out.println("</table></form>");
            if(booking_counter == 0) {
                out.println("<h3>Theres is no pending bookings at the moment.</h3>");
            }else {
                out.println("<h3>There are " + booking_counter + " bookings pending.</h3>");
            }
            
            statement.close();
            con.close();

        } catch (Exception ex) {
            out.println(ex);
        }
        %>
        
    </body>
</html>
