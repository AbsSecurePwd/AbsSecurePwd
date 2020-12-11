package Servlets;

import EJB.users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Kayee
 */
@WebServlet(urlPatterns = {"/signupServlet"})
public class signupServlet extends HttpServlet {
    
    @EJB private users users;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
           
        users.setUser(username, email, password);
        users.addUser();
        
        session.setAttribute("message", "User registered successfully.");
        response.sendRedirect("login.jsp");
    }
}
    