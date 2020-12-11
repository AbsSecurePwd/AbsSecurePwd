package EJB;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.ejb.Stateless;

/**
 * @author Kayee
 */
@Stateless
public class db_con {
    
    Connection con;
    private String url = "jdbc:derby://localhost:1527/sample";
    private String user = "app";
    private String psw = "app";
    
    public Connection getCon(){
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(url, user, psw);
            
            if(con == null) {
                System.out.println("Unable to create database connection...");
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return con;
    }
    
}
