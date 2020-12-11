/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import EJB.booking;
import EJB.db_con;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kayee
 */
@WebServlet(name = "cancelServlet", urlPatterns = {"/cancelServlet"})
public class cancelServlet extends HttpServlet {
    
    @EJB private booking booking;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        
        String bk_id = request.getParameter("hidden");
        
        booking.cancelBooking(bk_id);
        response.sendRedirect("booking.jsp");
        
    }
}
