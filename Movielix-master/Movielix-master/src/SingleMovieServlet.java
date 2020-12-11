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


@WebServlet(name = "SingleMovieServlet", urlPatterns = "/api/single-movie")
public class SingleMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
        response.setContentType("application/json"); // Response mime type
        PrintWriter out = response.getWriter();// get the printwriter for writing response
		
		
		String movieId = request.getParameter("id");
		String query = "select * from movies left join ratings on movies.id = ratings.movieId where movies.id = ?";
		
		 try {
			 	Context initCtx = new InitialContext();
	        	Context envCtx = (Context) initCtx.lookup("java:comp/env");
	            if (envCtx == null)
	                out.println("envCtx is NULL");
	            DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
	            Connection connection = ds.getConnection();
	    		PreparedStatement statement = connection.prepareStatement(query);
	    		statement.setString(1, movieId);
	    		ResultSet resultSet = statement.executeQuery();

	    		JsonObject jsonObject = new JsonObject();
	        
	            // Iterate through each row of rs
	            while (resultSet.next()) {
	            	String id = resultSet.getString("id");
	    			String title = resultSet.getString("title");
	    			String year = resultSet.getString("year");
	    			String director = resultSet.getString("director");
	    			String rating = resultSet.getString("rating");
	                
	    			
	    			
	    			JsonArray actorsArray = new JsonArray();
	    			String q = "select t1.name,t1.id from stars t1 inner join stars_in_movies t2 on t1.id = t2.starId inner join movies t3 on t2.movieId = t3.id where t3.title = ?";
	    			PreparedStatement s = connection.prepareStatement(q);
	    			s.setString(1, title);
	    			ResultSet rs = s.executeQuery();
	    			
	    			while(rs.next()) {
	    				JsonObject star = new JsonObject();
	        			String name = rs.getString("name");
	        			String starId = rs.getString("id");
	        			star.addProperty("name",name);
	        			star.addProperty("starId", starId);
	        			actorsArray.add(star);
	        		}
	    			
	    			String genres=" ";
	        		String q1 = "select t1.name from genres t1 inner join genres_in_movies t2 on t1.id = t2.genreId inner join movies t3 on t2.movieId = t3.id where t3.title = ?";
	    			//create another query to get genres
	    			PreparedStatement s1 = connection.prepareStatement(q1);
	    			s1.setString(1, title);
	        		ResultSet rs1 = s1.executeQuery();
	        		while(rs1.next()) {
	        			String genre = rs1.getString("name");
	        			genres += genre + " ";
	        		}
	        		
	        		
	        		
	        		jsonObject.addProperty("id", id);
	        		jsonObject.addProperty("title", title);
	        		jsonObject.addProperty("year", year);
	        		jsonObject.addProperty("director", director);
	        		jsonObject.addProperty("rating", rating);
	        		jsonObject.addProperty("genres", genres);
	        		jsonObject.add("actorsArray", actorsArray);
	        		
	        		
	        		
	        		s1.close();
	        		rs1.close();
	    			
	    			
	    			rs.close();
	        		s.close();
	    			
	            }
	    		

	            
	            out.write(jsonObject.toString());
	            response.setStatus(200);
	    		
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
