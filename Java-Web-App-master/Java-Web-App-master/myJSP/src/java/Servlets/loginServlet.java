package Servlets;

import EJB.db_con;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Kayee
 */
@WebServlet(urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    @EJB private db_con db_con;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        Boolean login_success = false;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String user_type = "";
        
        if(username.equals("") || password.equals("")) {
            session.setAttribute("message", "Username or password is incorrect. Please try again...");
            response.sendRedirect("login.jsp");           
            
        } else {

            db_con = new db_con();
            Connection connection = db_con.getCon();
            try{

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");

                while(resultSet.next()) {
                    String user_name = resultSet.getString("USER_NAME");
                    String user_password = resultSet.getString("USER_PASSWORD");
                    user_type = resultSet.getString("USER_TYPE");

                    if(username.equals(user_name)) {
                        // Password Decrytion
                        byte[] decodedBytes = Base64.getDecoder().decode(user_password);
                        String decodedString = new String(decodedBytes);

                        if(password.equals(decodedString)) {
                            login_success = true;
                            break;
                        }

                    } else {
                        login_success = false;
                    }
                }

                if(login_success) {
                    
                    session.setAttribute("username", username);
                    session.setAttribute("user_type", user_type);
                    response.sendRedirect("welcomePage.jsp");

                } else {
                    session.setAttribute("message", "Username or password is incorrect. Please try again...");
                    response.sendRedirect("login.jsp");
                }
                statement.close();
                connection.close();

            } catch(Exception e) {
                    out.println(e);
            } 
        }
    }
}
