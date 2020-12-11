

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mysql.jdbc.PreparedStatement;


@WebServlet(name = "PaymentServlet", urlPatterns = "/api/payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	String query = "select * from creditcards t1 where firstName = ? "
			+ "and lastName = ? and expiration = ? and id=?";

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String cardnumber = request.getParameter("cardnumber");
        String cardExpiration = request.getParameter("cardexpiration");
		
        
        
        //get user from session
        User user = (User) request.getSession().getAttribute("user");
        Map<String, String> userCart = user.getCart();
        
        String firstName = "";
        String lastName = "";
        //parse name
        if(name.split(" ").length > 1) {
        	firstName = name.split(" ")[0];
            lastName = name.split(" ")[1]; 
        }
        
        PrintWriter out = response.getWriter();// get the printwriter for writing response
        
        try {
        	Context initCtx = new InitialContext();
        	Context envCtx = (Context) initCtx.lookup("java:comp/env");
            if (envCtx == null)
                out.println("envCtx is NULL");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();
            
    		java.sql.PreparedStatement paymentStmt = connection.prepareStatement(query);
    		paymentStmt.setString(1, firstName);
    		paymentStmt.setString(2, lastName);
    		paymentStmt.setString(3, cardExpiration);
    		paymentStmt.setString(4, cardnumber);
    		
    		
    		ResultSet resultSet = paymentStmt.executeQuery();
    		Long id = 0L;
    		JsonObject responseJsonObject = new JsonObject();
    		if(resultSet.next()) { //card exists


    			for (Map.Entry<String, String> entry : userCart.entrySet()) {
    			    String movieId = entry.getKey();
    			    int quantity= Integer.parseInt(entry.getValue());
    			    
    			    for(int i = 0; i < quantity; i++) {
    			    	//create sales in table
                        String salesQuery="insert into sales (customerId, movieId, saleDate) values (?,?,curdate())";
                        resultSet.close();//close the result set to get another query
                        
                        //make new statement, insert sale into table
                        java.sql.PreparedStatement stmt = connection.prepareStatement(salesQuery, Statement.RETURN_GENERATED_KEYS);
                        stmt.setString(1, user.getId());
                        stmt.setString(2, movieId);
                        
                        
                        stmt.executeUpdate();
                        ResultSet rs = stmt.getGeneratedKeys();
                        if(rs!= null && rs.next()) {
                        	id = rs.getLong(1);
                        }
                        rs.close();
    			    }
                    
    			}

                
                responseJsonObject.addProperty("status", "success");
                responseJsonObject.addProperty("message", "success");
                responseJsonObject.addProperty("salesId", id.toString());
                response.getWriter().write(responseJsonObject.toString());
                
                
    		}else {//card doesnt exist
    			responseJsonObject.addProperty("status", "fail");
    			responseJsonObject.addProperty("message", "Credit card info is not valid");
    			response.getWriter().write(responseJsonObject.toString());
    		}
    		
    		paymentStmt.close();
    		connection.close();
    		
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	JsonObject responseJsonObject = new JsonObject();
	    	responseJsonObject.addProperty("status", "fail");
			responseJsonObject.addProperty("message", "something failed");
			response.getWriter().write(responseJsonObject.toString());
	    }
        
        out.close();
	           
	}

}
