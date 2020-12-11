package tesina.service;

import java.io.IOException;
import java.sql.*;

import com.itextpdf.text.DocumentException;

import tesina.data.Biglietto;
import tesina.data.Spettacolo;
import tesina.data.User;
import tesina.utils.DataBase;

public class BigliettoService {
	
	private Spettacolo spettacolo;
	private User userData;
	private Biglietto biglietto;
	
	private DataBase database;
	Connection conn;
	

	public BigliettoService(final Spettacolo spettacolo, final User userData) throws SQLException, ClassNotFoundException {
		database = new DataBase();
		conn = database.connect();
		this.spettacolo = spettacolo;
		this.userData = userData;
		this.biglietto = new Biglietto();
	}
	
	@Override
	public void finalize() throws SQLException {
		conn.close();
		database.close();
	}
	
	private int prenota(int posto[], int tipo) throws SQLException, DocumentException, IOException {
		String query = "SELECT cinema_dba.prenotazioni_seq.NEXTVAL FROM dual";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);
		result.next();
		
		int codice_prenotazione = result.getInt(1);
		
		biglietto.setTitolo('('+spettacolo.getTipo_3d()+") - "+spettacolo.getTitolo_film());
		biglietto.setCinema(spettacolo.getNome_cinema());
		biglietto.setData(spettacolo.getData_e_ora()); 
		biglietto.setSala(spettacolo.getNumero_sala());
		biglietto.setEmail(userData.getEmail());
			
		query = "INSERT INTO cinema_dba.prenotazioni(codice, utente, spettacolo, pagato, attiva) VALUES(?, ?, ?, 0, 1)";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setInt(1, codice_prenotazione);
		preparedStatement.setInt(2, userData.getId());
		preparedStatement.setInt(3, spettacolo.getCodice());
		preparedStatement.executeUpdate();
	
		
		for(int i=0; i<posto.length; i++) {
			query = "SELECT cinema_dba.posti_riservati_seq.NEXTVAL FROM dual";
			statement = conn.createStatement();
			result = statement.executeQuery(query);
			result.next();
			
			int codice_posto = result.getInt(1);
			
			query = "SELECT fila, numero FROM cinema_dba.posti WHERE codice=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, posto[i]);
			result = preparedStatement.executeQuery();
			result.next();
			
			biglietto.setFila(result.getString("fila"));
			biglietto.setNumero_posto(result.getInt("numero"));
			
			biglietto.setNumero_biglietto(new Integer(codice_prenotazione).toString().concat("-"+posto[i]));
			
			query = "INSERT INTO cinema_dba.posti_riservati(id, spettacolo, posto, prenotazione) VALUES(?, ?, ?, ?)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, codice_posto);
			preparedStatement.setInt(2, spettacolo.getCodice());
			preparedStatement.setInt(3, posto[i]);
			preparedStatement.setInt(4, codice_prenotazione);
			preparedStatement.executeUpdate();
			
			GeneratoreBigliettoPDF bigliettoPDF = new GeneratoreBigliettoPDF(biglietto);
			bigliettoPDF.generaPDF();
			
		}
		if(tipo==1) {
			query = "begin cinema_dba.checkPagamento(?); end;";
		    CallableStatement callStmt = conn.prepareCall(query);
		    callStmt.setInt(1, codice_prenotazione);
		    callStmt.execute();	
		    callStmt.close(); //ultimo utilizzo di callStmt
		}
			
			result.close(); //ultimo utilizzo di result
			statement.close(); //ultimo utilizzo di statement
			preparedStatement.close(); //ultimo utilizzo di preparedStatement
			
			
			return codice_prenotazione;
	}
	
	public int acquistaBigliettiCliente(int[] posto, int tipo) throws SQLException, DocumentException, IOException {
		
		int codice_prenotazione=-100;
		
		try {
			conn.setAutoCommit(false); //begin TRANSACTION
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); //imposto isolamento di tipo SERIALIZABLE
			codice_prenotazione = prenota(posto, tipo);
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
		
		return codice_prenotazione;
		
	}

	public int acquistaBigliettiGuest(int[] posto, int tipo) throws SQLException, DocumentException, IOException {
		
		int codice_utente=-100;
		int codice_prenotazione=-100;
		
		conn.setAutoCommit(false); //begin TRANSACTION
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); //imposto isolamento di tipo SERIALIZABLE
		
		try {
			
		//controllo se la mail ha già effettuato acquisti
		String query = "SELECT id AS codice FROM cinema_dba.utenti WHERE email=? AND tipo=-1";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1, userData.getEmail());
		ResultSet result = preparedStatement.executeQuery();
		
		if(result.next()) {
			userData.setId(result.getInt("codice"));
		} else {
			query = "INSERT INTO cinema_dba.utenti (id, username, password, nome, cognome, email, tipo) VALUES (cinema_dba.utenti_seq.NEXTVAL, ?, ?, ?, ?, ?, -1)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, userData.getUsername());
			preparedStatement.setString(2, userData.getPassword());
			preparedStatement.setString(3, userData.getNome());
			preparedStatement.setString(4, userData.getCognome());
			preparedStatement.setString(5, userData.getEmail());
			preparedStatement.executeUpdate();
		
			query = "SELECT id AS codice FROM cinema_dba.utenti WHERE email=? AND tipo=-1";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, userData.getEmail());
			result = preparedStatement.executeQuery();
			
			result.next();
			
			userData.setId(result.getInt("codice"));
		}
			result.close();
			preparedStatement.close();
		
			codice_prenotazione = prenota(posto, tipo);
			
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
		
		return codice_prenotazione;
	}

	public void setUserData(User userData2) {
		this.userData = userData2;
		
	}
}
