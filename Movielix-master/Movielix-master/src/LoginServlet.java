import com.google.gson.*;
import org.jasypt.util.password.StrongPasswordEncryptor;
import com.mysql.jdbc.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "LoginServlet", urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
	String query = "select * from customers t1 where email=?";
	String employee_query = "select * from employees t1 where email=?";
    
	


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String employee = request.getParameter("employee");
        String userAgent = request.getHeader("User-Agent");
        
        response.setContentType("application/json"); // Response mime type        
        PrintWriter out = response.getWriter();// get the printwriter for writing response
        
        try {
        	Context initCtx = new InitialContext();
        	Context envCtx = (Context) initCtx.lookup("java:comp/env");
            if (envCtx == null)
                out.println("envCtx is NULL");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();
        	
        	
    		java.sql.PreparedStatement loginStmt;
    		if(employee == null || employee.equals("true") ==false)
    			loginStmt = connection.prepareStatement(query);
    		else
    			loginStmt = connection.prepareStatement(employee_query);
    			
    		
    		loginStmt.setString(1, email);
    		ResultSet rs = loginStmt.executeQuery();
    		
    		boolean success = false;
    		JsonObject responseJsonObject = new JsonObject();
    		if(rs.next()) { //user exists
    			
    			String encryptedPassword = rs.getString("password");
    			if(employee == null || employee.equals("true") ==false)
    				success = new StrongPasswordEncryptor().checkPassword(password,encryptedPassword);
    			else {
    				success = password.equals(encryptedPassword);
    			}
                //create user for session
                if(success && (employee == null || employee.equals("true") ==false)) {
                	String id = rs.getString("id");
	                request.getSession().setAttribute("user", new User(email,id));
	                responseJsonObject.addProperty("status", "success");
	                responseJsonObject.addProperty("message", "success");
	                response.getWriter().write(responseJsonObject.toString());
                }
                else if(success) {
                	request.getSession().setAttribute("user", new User(email));
                	responseJsonObject.addProperty("status", "success");
	                responseJsonObject.addProperty("message", "success");
	                response.getWriter().write(responseJsonObject.toString());
                }
                else {
                	responseJsonObject.addProperty("status", "fail");
        			responseJsonObject.addProperty("message", "Email or Password was not correct");
        			response.getWriter().write(responseJsonObject.toString());
                }
    		}else {//user doesnt exist
    			responseJsonObject.addProperty("status", "fail");
    			responseJsonObject.addProperty("message", "Email or Password was not correct");
    			response.getWriter().write(responseJsonObject.toString());
    		}
    		
    		rs.close();
    		loginStmt.close();
    		connection.close();
    		
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
        out.close();
       
    }
}
