

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet(name = "DashboardServlet", urlPatterns = "/api/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json"); // Response mime type
        PrintWriter out = response.getWriter();// get the printwriter for writing response
        try {
        	Context initCtx = new InitialContext();
        	Context envCtx = (Context) initCtx.lookup("java:comp/env");
            if (envCtx == null)
                out.println("envCtx is NULL");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();
    		
    		ResultSet resultSet ;
    		
    		// --- LISTING DATABASE TABLE NAMES ---
    	     String[] types = { "TABLE" };
    	     resultSet = connection.getMetaData()
    	         .getTables("moviedb", null, "%", types);

    	     JsonArray jsonArray = new JsonArray();
    	     
    	     String tableName="";
    	     
    	     while (resultSet.next()) {
    	    	 
    	    	 JsonObject jsonObject = new JsonObject();
    	    	 
    	       tableName = resultSet.getString(3);
    	       jsonObject.addProperty("tableName", tableName);
    	       
    	       DatabaseMetaData meta = connection.getMetaData();
    	       ResultSet metaresultSet = meta.getColumns("moviedb", null, tableName, "%");
    	       JsonArray innerArray = new JsonArray();
    	       while(metaresultSet.next()) {
//    	    	   System.out.println("Column Name of table " + tableName + " = "
//    	    	           + metaresultSet.getString(4) + " " + metaresultSet.getString(6));
    	    	   JsonObject columnObject = new JsonObject();
    	    	   columnObject.addProperty("columnName", metaresultSet.getString(4));
    	    	   columnObject.addProperty("type", metaresultSet.getString(6));
    	    	   innerArray.add(columnObject);
    	       }
    	       jsonObject.add("columns", innerArray);
    	       jsonArray.add(jsonObject);
    	       
    	       
    	       metaresultSet.close();
    	     }
    	     resultSet.close();
    	     
    	     out.write(jsonArray.toString());
	         response.setStatus(200);
    		
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        response.setContentType("application/json"); // Response mime type
        PrintWriter out = response.getWriter();// get the printwriter for writing response
		
		String name = request.getParameter("star_name");
		String year = request.getParameter("star_birth_year"); 
		
		String star_query = "{call insertstar(?,?)}";
		String movie_query = "{call addmovie(?,?,?,?,?)}";
		
		String title = request.getParameter("movie_title");
		String m_year = request.getParameter("movie_year");
		String director = request.getParameter("movie_director");
		String star = request.getParameter("movie_star");
		String genre = request.getParameter("movie_genre");
		
		JsonObject responseJsonObject = new JsonObject();
		
		
		
		try {
			Context initCtx = new InitialContext();
        	Context envCtx = (Context) initCtx.lookup("java:comp/env");
            if (envCtx == null)
                out.println("envCtx is NULL");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();
    		
    		CallableStatement stmt;

    		if(name == null) {
    			
    			stmt =  connection.prepareCall(movie_query);
    			stmt.setString(1, title);
        		stmt.setInt(2, Integer.parseInt(m_year));
        		stmt.setString(3, director);
        		stmt.setString(4, star);
        		stmt.setString(5, genre);
    		}else {
    			stmt = connection.prepareCall(star_query);
    			stmt.setString(1, name); //for star
        		if(year == null || year.equals("") == true)	{
        			stmt.setNull(2, Types.INTEGER);
        		}else {
        			stmt.setInt(2, Integer.parseInt(year));
        		}
    		}
    		
    		ResultSet rs = stmt.executeQuery();
    		String result = "";
    		while(rs.next()) {
    			result = rs.getString(1);
    		}
    		rs.close();
    		if(result.equals("movie exist")) {
				responseJsonObject.addProperty("status", "fail");
		    	if(name != null) {
		    		responseJsonObject.addProperty("message", "New row could not be added");
		    	}else {
		    		responseJsonObject.addProperty("message", "Movie already exists");
		    	}
				
				response.getWriter().write(responseJsonObject.toString());
				// set reponse status to 500 (Internal Server Error)
				response.setStatus(200);
				out.close();
			}else {
				responseJsonObject.addProperty("status", "success");
	        	if(name != null)
	        		responseJsonObject.addProperty("message", name + " was inserted into the database");
	        	else {
	        		responseJsonObject.addProperty("message", title + " was inserted into the database");
	        	}
	            out.write(responseJsonObject.toString());
	            response.setStatus(200);
	            stmt.close();
	    		connection.close();
	    		out.close();
			}
    		
    		
        	
    		
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	responseJsonObject.addProperty("status", "fail");
	    	if(name != null) {
	    		responseJsonObject.addProperty("message", "New row could not be added");
	    	}else {
	    		responseJsonObject.addProperty("message", "Movie already exists");
	    	}
			
			response.getWriter().write(responseJsonObject.toString());
			// set reponse status to 500 (Internal Server Error)
			response.setStatus(500);
			out.close();
	    }
	}

}
