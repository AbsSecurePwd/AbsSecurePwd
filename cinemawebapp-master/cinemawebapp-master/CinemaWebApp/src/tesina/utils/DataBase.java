package tesina.utils;

import java.sql.*;

public class DataBase {

	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe",
								username = "web_app",
								password = "onlinestore";

	private Connection conn = null;
	
	public DataBase() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver"); //carico il driver JDBC
	}
	
	public Connection connect() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, username, password);		
		return conn;
	}
	
	public void close() throws SQLException {
		conn.close();
		return;
	}

}
