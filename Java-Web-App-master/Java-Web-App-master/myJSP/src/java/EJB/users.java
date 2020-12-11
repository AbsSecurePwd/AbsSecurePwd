package EJB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Kayee
 */
@Stateless
public class users {

    String username;
    String email;
    String password;
    
    public void setUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    @EJB private db_con db_con;
    public void addUser(){
        
        Connection connection = db_con.getCon();
        try{
            Statement statement = connection.createStatement();

            // Password Encrytption 
            String encodedString = Base64.getEncoder().withoutPadding().encodeToString(password.getBytes());
            
            String SQL = "INSERT INTO USERS(USER_NAME, USER_EMAIL, USER_PASSWORD, USER_TYPE) "
                + "VALUES ('" + username + "','" + email + "','" + encodedString + "','user')";
            statement.executeUpdate(SQL); 
            statement.close();
            connection.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

}
