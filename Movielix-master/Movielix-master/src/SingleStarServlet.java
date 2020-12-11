

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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


@WebServlet(name = "SingleStarServlet", urlPatterns = "/api/single-star")
public class SingleStarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
        response.setContentType("application/json"); // Response mime type
        String id = request.getParameter("id");
        String nameParam = request.getParameter("name");
        
        String query = "select * from stars where id=?";
        String queryFromName = "select * from stars where name=?";
        String moviesQuery = "select t1.id, t1.title from movies t1 left join stars_in_movies t2 on t1.id = t2.movieId left join stars t3 on t2.starId = t3.id where t3.id = ?";
        PrintWriter out = response.getWriter();// get the printwriter for writing response
		
//        if(id == null || id.equals("")) {
//        	System.out.println("id given");
//        	System.out.println(id);
//        }
//        if(nameParam == null || nameParam.equals("")) {
//        	System.out.println("name given");
//        	System.out.println(nameParam);
//        }
		
		 try {
			 Context initCtx = new InitialContext();
	        	Context envCtx = (Context) initCtx.lookup("java:comp/env");
	            if (envCtx == null)
	                out.println("envCtx is NULL");
	            DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
	            Connection connection = ds.getConnection();
	    		
	    		
	    		PreparedStatement statement;
	    		
//	    		if(nameParam == null || nameParam.equals("")) {
	    			System.out.println(id);
	    			statement = connection.prepareStatement(query);
		    		statement.setString(1, id);
//	    		}else {
//	    			System.out.println(nameParam);
//	    			statement = connection.prepareStatement(queryFromName);
//		    		statement.setString(1, nameParam);
//	    		}
	    		
	    		
	    		
	    		
	    		ResultSet resultSet = statement.executeQuery();

	    		JsonObject jsonObject = new JsonObject();
	        
	            // Iterate through each row of rs
	            while (resultSet.next()) {
	            	id = resultSet.getString("id");
	                String name = resultSet.getString("name");
	                String birthYear = resultSet.getString("birthYear");
	                jsonObject.addProperty("name", name);
	                jsonObject.add("id", null);
	                if(birthYear != null)
	                	jsonObject.addProperty("birthYear", birthYear);
	                else
	                	jsonObject.addProperty("birthYear", "");
	            }
	            
	            JsonArray moviesArray = new JsonArray();
	            PreparedStatement s = connection.prepareStatement(moviesQuery);
	            s.setString(1, id);
	    		ResultSet rs = s.executeQuery();
	    		while (rs.next()) {
	    			String movieId = rs.getString("id");
	    			String movieTitle = rs.getString("title");
	    			
	    			JsonObject movie = new JsonObject();
	    			movie.addProperty("movieId", movieId);
	    			movie.addProperty("movieTitle", movieTitle);
	    			moviesArray.add(movie);
	    			
	    		}
	    		
	    		jsonObject.add("movies", moviesArray);
	    		
	            out.write(jsonObject.toString());
	            response.setStatus(200);
	    		
	            s.close();
	            rs.close();
	            
	    		resultSet.close();
	    		statement.close();
	    		connection.close();
	    		
		    } catch (Exception e) {
		   
		    	e.printStackTrace();
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
