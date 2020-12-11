

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet(name = "StoreCartServlet", urlPatterns = "/api/storecart")
public class StoreCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
    
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("application/json"); // Response mime type
	     PrintWriter out = response.getWriter();// get the printwriter for writing response
				 
			
			 
		     
		     User user = (User) request.getSession().getAttribute("user");	
		     Map<String, String> cart = user.getCart();
	
		     JsonArray cartArray = new JsonArray();
		     
		     for (Map.Entry<String, String> entry : cart.entrySet()) {
		    	JsonObject ob = new JsonObject(); 
		    	 String key = entry.getKey();
		    	 String val = entry.getValue();
		    	 ob.addProperty("title", key);
			     ob.addProperty("quantity", val);
			    	 
			    	 cartArray.add(ob);
		    	 
		     }
		     
			System.out.println(cartArray.toString());
		     out.write(cartArray.toString());
	         response.setStatus(200);
	         
		out.close();
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
		User user = (User) request.getSession().getAttribute("user");
		
		Map<String, String> cart = new HashMap<>();
		for(int i = 0; i < parameterNames.size(); i++) {
			if(request.getParameter(parameterNames.get(i)).equals("0") == false) {
				cart.put(parameterNames.get(i), request.getParameter(parameterNames.get(i)));
			}
			
//			System.out.println(request.getParameter(parameterNames.get(i)));
		}
		
		user.setCart(cart);
		request.getSession().setAttribute("user", user);
		
		
		JsonObject responseJsonObject = new JsonObject();
        responseJsonObject.addProperty("status", "success");
        responseJsonObject.addProperty("message", "success");

        response.getWriter().write(responseJsonObject.toString());
  
		
		
	}

}
