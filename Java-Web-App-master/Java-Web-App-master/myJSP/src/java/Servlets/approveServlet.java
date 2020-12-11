/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import EJB.booking;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kayee
 */
@WebServlet(urlPatterns = {"/approveServlet"})
public class approveServlet extends HttpServlet {
    
    @EJB private booking booking;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        
        // get id from hidden field to identify which booking to approve
        String approve_id = request.getParameter("hidden"); 
        int id = Integer.parseInt(approve_id);
        
        booking.approvelBooking(approve_id);
        response.sendRedirect("approveBooking.jsp");
        
    }
}
