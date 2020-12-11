import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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


@WebServlet(name = "MovieServlet", urlPatterns = "/api/movielist")
public class MovieListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String genre_query = "select movies.id,movies.title,movies.year, movies.director,rating from "
			+ "movies inner join ratings on movies.id=ratings.movieId where movies.id in "
			+ "(select movieId from genres_in_movies inner join genres on genres.id=genres_in_movies.genreId where genres.name=?) order by ? "; 
	private String genre_query_2 = " limit ? offset ?";
	
//	private String search_query = "select t1.id, t1.title, t1.year, t1.director, t2.rating"
//							+	" from movies t1 inner join ratings t2 on t1.id = t2.movieId where t1.title like ? and t1.year=? and t1.director like ?"; //orderBy t1.title asc limit 10 offset 0";//and year=? and director like '?%' orderBy title asc limit 10 offset 0";
       
	private String title_query = "select * from movies where title like ? order by ? ";
	private String title_query_2 = " limit ? offset ?";
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();// get the printwriter for writing response
        response.setContentType("application/json"); // Response mime type

		//from browse 
		String genre = request.getParameter("genre");
		String paramtitle = request.getParameter("title");
		
//		//from search
////		String paramYear = request.getParameter("year");
////		String paramDirector = request.getParameter("director");
////        String paramStar = request.getParameter("star");
//
		//pagination
		String limit = request.getParameter("limit");
		String offset = request.getParameter("offset");
////		
		String sortBy = request.getParameter("sortBy");
		String ascending = request.getParameter("ascending");
//
//		
        
        
		
		
		 try {
		 	Context initCtx = new InitialContext();
        	Context envCtx = (Context) initCtx.lookup("java:comp/env");
            if (envCtx == null)
                out.println("envCtx is NULL");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();
	    		
	    	java.sql.PreparedStatement browseStmt;
	    		
    		if(genre != null) {
    			if(ascending.equals("asc")) {
    				browseStmt = connection.prepareStatement(genre_query + "asc" + genre_query_2);
    			}else {
    				browseStmt = connection.prepareStatement(genre_query + "desc" + genre_query_2);
    			}
	    		browseStmt.setString(1, genre);
    		}else {
    			if(ascending.equals("asc")) {
    				browseStmt = connection.prepareStatement(title_query + "asc" + title_query_2);
    			}else {
    				browseStmt = connection.prepareStatement(title_query + "desc" + title_query_2);
    			}
    			browseStmt.setString(1, paramtitle+"%");
    		}
    		
    		browseStmt.setString(2, sortBy);
    		browseStmt.setInt(3, Integer.parseInt(limit));
    		browseStmt.setInt(4, Integer.parseInt(offset));
	    
    		System.out.println(browseStmt.toString());
    		
    		
    			ResultSet resultSet = browseStmt.executeQuery();

	            JsonArray jsonArray = new JsonArray();
	        
	            while (resultSet.next()) {
	            	String id = resultSet.getString("id");
	                String title = resultSet.getString("title");
	                String year = resultSet.getString("year");
	                String director = resultSet.getString("director");
//	                String rating= resultSet.getString("rating");
	                
	                System.out.println(title);
	                
	                String rating_query = "select * from ratings where movieId = ?";
	                java.sql.PreparedStatement rating_s = connection.prepareStatement(rating_query);
	                rating_s.setString(1, id);
	                ResultSet rating_rs = rating_s.executeQuery();
	                String rating="";
	                while(rating_rs.next()) {
	                	rating = rating_rs.getString("rating");
	                }
	                
	                rating_rs.close();
	                rating_s.close();
	                
	                JsonArray actorInfo = new JsonArray();
	                //create query for actors
	                String actor_query = "select t1.name,t1.id from stars t1 inner join stars_in_movies t2 on t1.id = t2.starId inner join movies t3 on t2.movieId = t3.id where t3.title = ?";
	                java.sql.PreparedStatement s = connection.prepareStatement(actor_query);
	                s.setString(1,title);
	        		ResultSet rs = s.executeQuery();
	        		while(rs.next()) {
	        			JsonObject actor = new JsonObject();
	        			String name = rs.getString("name");
	        			String actorId = rs.getString("id");
	        			actor.addProperty("name", name);
	        			actor.addProperty("id", actorId);
	        			actorInfo.add(actor);
	        		}
	        		rs.close();
	        		s.close();
	    			
	        		
	        		JsonArray genres = new JsonArray();
	        		String genres_query = "select t1.name from genres t1 inner join genres_in_movies t2 on t1.id = t2.genreId inner join movies t3 on t2.movieId = t3.id where t3.title =?";
	        		java.sql.PreparedStatement s1 = connection.prepareStatement(genres_query);
	        		s1.setString(1, title);
	        		ResultSet rs1 = s1.executeQuery();
	        		while(rs1.next()) {
	        			
	        			JsonObject genre_ = new JsonObject();
	        			String n = rs1.getString("name");
	        			genre_.addProperty("name", n);
	        			
	        			genres.add(genre_);
	        		}
	        		rs1.close();
	        		s1.close();
	    			
	                
	                
	                // Create a JsonObject based on the data we retrieve from rs
	                JsonObject jsonObject = new JsonObject();
	                jsonObject.addProperty("id", id);
	                jsonObject.addProperty("title", title);
	                jsonObject.addProperty("year", year);
	                jsonObject.addProperty("director", director);
	                jsonObject.addProperty("rating", rating);
	                jsonObject.add("genres", genres);
	                jsonObject.add("actors", actorInfo);
	                jsonArray.add(jsonObject);
	            }
	    		
	            out.write(jsonArray.toString());
	            response.setStatus(200);
	    		
	    		resultSet.close();
	    		browseStmt.close();
	    		connection.close();
	    		
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	// write error message JSON object to output
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("errorMessage", e.getMessage());
				out.write(jsonObject.toString());

				// set response status to 500 (Internal Server Error)
				response.setStatus(500);
		    }
		    
		    out.close();

	}
	


}
