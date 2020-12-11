package tesina.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tesina.data.User;
import tesina.utils.DataBase;

public class UserService {
	
	private DataBase database;
	Connection conn;

	public UserService() throws SQLException, ClassNotFoundException {
		database = new DataBase();
		conn = database.connect();
	}
	
	@Override
	public void finalize() throws SQLException {
		conn.close();
		database.close();
	}
	
	public User loginCliente(String username, String password) throws SQLException {
		User userData = null;
		String query = "SELECT id, username, nome, cognome, email FROM cinema_dba.utenti WHERE username=? AND password=? AND tipo=0";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, username);
		statement.setString(2, password);
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			userData = new User(result.getInt("id"), result.getString("username"), result.getString("nome"), result.getString("cognome"), result.getString("email"), 0);
		} else {
			userData = null;
		}
		
		result.close();
		statement.close();
		
		return userData;
		
	}
	
	public User signupCliente(User userData) throws SQLException {
		
		// prima di registrare il cliente, controllo che non sia stato in precedenza un nostro utente anonimo
		String query = "SELECT id AS codice FROM cinema_dba.utenti WHERE email=? AND tipo=-1";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, userData.getEmail());
		ResultSet result = statement.executeQuery();
		try {
			
			conn.setAutoCommit(false); //begin TRANSACTION
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); //imposto isolamento di tipo SERIALIZABLE
			
			if(result.next()) {
				int codice_utente = result.getInt("codice");
				query = "UPDATE cinema_dba.utenti SET username=?, password=?, nome=?, cognome=?, tipo=0 WHERE id=?";
				statement = conn.prepareStatement(query);
				statement.setString(1, userData.getUsername());
				statement.setString(2, userData.getPassword());
				statement.setString(3, userData.getNome());
				statement.setString(4, userData.getCognome());
				statement.setInt(5, codice_utente);
				statement.executeUpdate();
			} else {
				query = "INSERT INTO cinema_dba.utenti (id, username, password, nome, cognome, email, tipo) VALUES (cinema_dba.utenti_seq.nextval, ?, ?, ?, ?, ?, 0)";
				statement = conn.prepareStatement(query);
				statement.setString(1, userData.getUsername());
				statement.setString(2, userData.getPassword());
				statement.setString(3, userData.getNome());
				statement.setString(4, userData.getCognome());
				statement.setString(5, userData.getEmail());
				statement.executeUpdate();
			}
			result.close();
			statement.close();
			
			userData = loginCliente(userData.getUsername(), userData.getPassword());
			
			conn.commit(); //end TRANSACTION
			
		} catch(SQLException se) {
			
		      se.printStackTrace();
		      System.out.println("Rollback...");
			  try{
				 if(conn!=null)
		            conn.rollback();
				 throw se;
		      }catch(SQLException se2){
		         se2.printStackTrace();
		         throw se2;
		      }
		   }
		finally {
			conn.setAutoCommit(true); //ritorno in auto-commit
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); //ritorno all'isolamento di default
		}
		
		return userData;
	}
	
}
