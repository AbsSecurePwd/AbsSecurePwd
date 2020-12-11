

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet(name = "BrowseServlet", urlPatterns = "/api/browse")
public class BrowseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
        String query = "select * from genres;";
        
        response.setContentType("application/json"); // Response mime type        
        PrintWriter out = response.getWriter();// get the printwriter for writing response

        try {
    		
        	Context initCtx = new InitialContext();
        	Context envCtx = (Context) initCtx.lookup("java:comp/env");
            if (envCtx == null)
                out.println("envCtx is NULL");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();
        	
    		java.sql.PreparedStatement browseStmt = connection.prepareStatement(query); 
    				
    		ResultSet resultSet = browseStmt.executeQuery();

            JsonArray jsonArray = new JsonArray();
        
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                

                // Create a JsonObject based on the data we retrieve from rs
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", name);

                jsonArray.add(jsonObject);
            }
    		
            out.write(jsonArray.toString());
            response.setStatus(200);
    		
    		resultSet.close();
    		browseStmt.close();
    		connection.close();
    		
	    } catch (Exception e) {
	   
	    	// write error message JSON object to output
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("errorMessage", e.getMessage());
			out.write(jsonObject.toString());

			// set reponse status to 500 (Internal Server Error)
			response.setStatus(500);
	    }
	    
	    out.close();
	}


}
