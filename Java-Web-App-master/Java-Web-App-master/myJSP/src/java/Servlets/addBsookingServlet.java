package Servlets;

import EJB.booking;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author Kayee
 */
@WebServlet(urlPatterns = {"/addBookingServlet"})
public class addBsookingServlet extends HttpServlet {
    
    @EJB private booking booking;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        
        String booking_name = request.getParameter("bk_name");
        String booking_date = request.getParameter("date");
        
        booking.addBooking(booking_name, booking_date);
        response.sendRedirect("booking.jsp");

    }
}
