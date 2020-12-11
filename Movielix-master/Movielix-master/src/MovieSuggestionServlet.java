

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

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
import com.mysql.jdbc.Statement;


@WebServlet("/movie-suggestion"/*name = "MovieSuggestionServlet", urlPatterns = "/api/MovieSuggestion"*/)
public class MovieSuggestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    private String movie_query = "select * from movies where match(title) against ( ? in boolean mode) limit 10";
    
    private String m1 = "select * from movies where match(title) against ( '";
    private String m2 = "' in boolean mode) limit 10";
	
    private String android_query = "select * from movies where match(title) against ( ? in boolean mode) limit 5 offset ?";
    
    
    private void writeResult(String stat, long elapsedTime) {
    	
    	BufferedWriter bw = null;
		FileWriter fw = null;
    	
    	String contextPath = getServletContext().getRealPath("/");
    	String filePath = contextPath + "scalednops.txt";
    	File file = new File(filePath);
    	if(!file.exists()) {
    		try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
		try {
	    	fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(stat + String.valueOf(elapsedTime) + "\n");
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   	
    	doGet(request, response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long JstartTime = System.nanoTime();
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JsonArray jsonArray = new JsonArray();
		String query = request.getParameter("query");
		String offset = request.getParameter("offset");
		
		
		String userAgent = request.getHeader("User-Agent");
		

		String [] queryArray = query.split(" ");
		String formattedQuery = "";
		for(int i = 0;i < queryArray.length; i++) {
			formattedQuery += " +" + queryArray[i] + "*";
		}
		

		
		
		 try {
			 Context initCtx = new InitialContext();
			 Context envCtx = (Context) initCtx.lookup("java:comp/env");
	         if (envCtx == null)
	        	 out.println("envCtx is NULL");
	         DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
	         Connection connection = ds.getConnection();
	         
	    		
	    	java.sql.PreparedStatement movieStmt;
//	    	
////	    	if(userAgent.contains("Android")) {
////	    		movieStmt = connection.prepareStatement(android_query);
////	    		movieStmt.setString(1, formattedQuery);
////	    		movieStmt.setInt(2, Integer.parseInt(offset));
////	    		ResultSet resultSet = movieStmt.executeQuery();
////	    		
////	    		while(resultSet.next()) {
////	            	String id = resultSet.getString("id");
////	                String title = resultSet.getString("title");
////	                String year = resultSet.getString("year");
////	                String director = resultSet.getString("director");
////	                
////
////	                
////	                
////	                JsonArray actorInfo = new JsonArray();
////	                //create query for actors
//////	                String actor_query = "select t1.name from stars t1 inner join stars_in_movies t2 on t1.id = t2.starId inner join movies t3 on t2.movieId = t3.id where t3.title = ?";
////	               String actor_query = " select name from stars where id in (select starId from stars_in_movies where movieId in (select id from movies where title = ?))";
////	                java.sql.PreparedStatement s = connection.prepareStatement(actor_query);
////	                s.setString(1,title);
////	        		ResultSet rs = s.executeQuery();
////	        		while(rs.next()) {
////	        			JsonObject actor = new JsonObject();
////	        			String name = rs.getString("name");
////	        			actor.addProperty("name", name);
////	        			actorInfo.add(actor);
////	        		}
////	        		rs.close();
////	        		s.close();
////	                
////	        		JsonArray genres = new JsonArray();
////	        		String genres_query = "select name from genres where id in (select genreId from genres_in_movies where movieId in (select id from movies where title = ?))";
////	        		java.sql.PreparedStatement s1 = connection.prepareStatement(genres_query);
////	        		s1.setString(1, title);
////	        		ResultSet rs1 = s1.executeQuery();
////	        		while(rs1.next()) {
////	        			
////	        			JsonObject genre_ = new JsonObject();
////	        			String n = rs1.getString("name");
////	        			genre_.addProperty("name", n);
////	        			
////	        			genres.add(genre_);
////	        		}
////	        		rs1.close();
////	        		s1.close();
////	    			
////	                JsonObject jsonObject = new JsonObject();
////	                jsonObject.addProperty("id", id);
////	                jsonObject.addProperty("title", title);
////	                jsonObject.addProperty("year", year);
////	                jsonObject.addProperty("director", director);
////	                jsonObject.add("genres", genres);
////	                jsonObject.add("actors", actorInfo);
////	                jsonArray.add(jsonObject);
////	    		}
////	    		System.out.println(jsonArray.toString());
////	            out.write(jsonArray.toString());
////	            response.setStatus(200);
////	            resultSet.close();
////	    		
////	    		
////	    	}else { //web query
	    		
//	    		movieStmt = connection.createStatement();
	    		
	    		movieStmt = connection.prepareStatement(movie_query);
	    		movieStmt.setString(1, formattedQuery);
	    		
	    		long startTime = System.nanoTime();
//	    		ResultSet resultSet = movieStmt.executeQuery(m1+ formattedQuery + m2);
	    		ResultSet resultSet = movieStmt.executeQuery();
	    		
		    	long endTime = System.nanoTime();
		    	long elapsedTime = endTime - startTime; // elapsed time in nano seconds
		    	writeResult("ts: ", elapsedTime);
		    	
		    	while(resultSet.next()) {
		    		String id = resultSet.getString("id");
		    		String title = resultSet.getString("title");
		    		jsonArray.add(generateJsonObject(id,title));
		    	}
	    		
		    	System.out.println(jsonArray.toString());
	            out.write(jsonArray.toString());
	            response.setStatus(200);
	            resultSet.close();
//	    	}
	    	
	    	
	    	
    		
    		
    		movieStmt.close();
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
		    long endTime = System.nanoTime();
	    	long elapsedTime = endTime - JstartTime; // elapsed time in nano seconds. Note: print the values in nano seconds
	    	writeResult("tj: ", elapsedTime);
	}
	
	private static JsonObject generateJsonObject(String movieID, String movieName) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("value", movieName);
		
		JsonObject additionalDataJsonObject = new JsonObject();
		additionalDataJsonObject.addProperty("movieID", movieID);
		
		jsonObject.add("data", additionalDataJsonObject);
		return jsonObject;
	}
}
