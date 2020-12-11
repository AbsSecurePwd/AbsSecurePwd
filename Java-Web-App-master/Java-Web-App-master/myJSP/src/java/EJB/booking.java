package EJB;

import java.sql.Connection;
import java.sql.Statement;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Kayee
 */
@Stateless
public class booking {
  
    @EJB private db_con db_con;
    public void addBooking(String booking_name, String booking_date){
                
        Connection connection = db_con.getCon();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO BOOKING(BK_NAME, BK_DATE, BK_STATUS) "
                + "VALUES ('" + booking_name + "','" + booking_date + "','Pending')");

            statement.close();
            connection.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void cancelBooking(String bk_id){
                
        Connection connection = db_con.getCon();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE BOOKING "
            + "SET BK_STATUS = 'Cancelled' WHERE BK_ID = "+bk_id+"");

            statement.close();
            connection.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void approvelBooking(String approve_id){
        
        Connection connection = db_con.getCon();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE BOOKING "
            + "SET BK_STATUS = 'Approved' WHERE BK_ID = "+approve_id+"");

            statement.close();
            connection.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
 
}
