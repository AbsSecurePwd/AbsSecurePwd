package tesina.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import tesina.data.Posto;
import tesina.data.Spettacolo;
import tesina.utils.DataBase;

public class SpettacoloService {
	
	private DataBase database;
	Connection conn;

	public SpettacoloService() throws SQLException, ClassNotFoundException {
		database = new DataBase();
		conn = database.connect();
	}
	
	@Override
	public void finalize() throws SQLException {
		conn.close();
		database.close();
	}
	
	public Spettacolo[] listaSpettacoli(ArrayList<String> listaCinema_tmp) throws SQLException {
		String query = "SELECT codice, codice_sala, nome_cinema, titolo, durata, numero_sala, data_e_ora, prezzo, tipo_3d FROM cinema_dba.mv_spettacoli_attivi ORDER BY data_e_ora";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		ArrayList<Spettacolo> listaSpettacoli = new ArrayList<Spettacolo>();
		while(result.next()) {
			listaSpettacoli.add(new Spettacolo(result.getInt("codice"), result.getInt("codice_sala"), result.getString("nome_cinema"), result.getString("titolo"), result.getInt("durata"), 
														result.getInt("numero_sala"), result.getString("data_e_ora"), result.getFloat("prezzo"), 
														result.getInt("tipo_3d")));
		}
		
		result = conn.createStatement().executeQuery("SELECT DISTINCT nome_cinema FROM cinema_dba.mv_spettacoli_attivi ORDER BY nome_cinema");
		while(result.next()) {
			listaCinema_tmp.add(result.getString(1));
		}
		
		result.close();
		statement.close();
		
		if(listaSpettacoli.isEmpty()) return null;
		
		return (Spettacolo[]) listaSpettacoli.toArray(new Spettacolo[listaSpettacoli.size()]);
	}

	public Spettacolo caricaSpettacolo(int codice_spettacolo) throws SQLException {
		String query = "SELECT codice, codice_sala, nome_cinema, titolo, durata, numero_sala, data_e_ora, prezzo, tipo_3d "
				+ "FROM cinema_dba.mv_spettacoli_attivi WHERE codice=?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, codice_spettacolo);
		ResultSet result = statement.executeQuery();
		
		Spettacolo spettacolo = null;
		
		if(result.next()) {
			spettacolo = new Spettacolo(result.getInt("codice"), result.getInt("codice_sala"), result.getString("nome_cinema"), result.getString("titolo"), result.getInt("durata"), 
					result.getInt("numero_sala"), result.getString("data_e_ora"), result.getFloat("prezzo"), 
					result.getInt("tipo_3d"));
		}
		
		result.close();
		statement.close();
		
		return spettacolo;
	}
	
	  public Posto[] getPostiSala(int codice_sala) throws SQLException {
		  String query = "SELECT P.codice, P.fila, P.numero FROM cinema_dba.posti P"
		  		+ " WHERE P.sala=?"
		  		+ " ORDER BY P.fila, P.numero, P.codice";
		  PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, codice_sala);
			ResultSet result = statement.executeQuery();
			
			ArrayList<Posto> listaPosti = new ArrayList<Posto>();
			while(result.next()) {
				listaPosti.add(new Posto(result.getInt("codice"), result.getString("fila"), result.getInt("numero")));
			}
			
			result.close();
			statement.close();
			
			if(listaPosti.isEmpty()) {
				return null;
			} else {
				return (Posto[]) listaPosti.toArray(new Posto[listaPosti.size()]);
			}
	  }
	  
	public void setPostiOccupati(int codice_spettacolo, Posto[] posti) throws SQLException {
		String query = "SELECT PR.posto AS codice_posto"
				+ " FROM cinema_dba.posti_riservati PR JOIN cinema_dba.prenotazioni PRE ON PR.prenotazione=PRE.codice"
				+ " WHERE PR.spettacolo=? AND PRE.attiva=1";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, codice_spettacolo);
		ResultSet result = statement.executeQuery();
		
		ArrayList<Posto> listaPosti = new ArrayList<Posto>();
		int codice_tmp;
		boolean trovato;
		int i;
		while(result.next()) { //setto il posto come occupato
			codice_tmp = result.getInt("codice_posto");
			trovato = false;
			i=0;
			while(!trovato && i<posti.length) { //i<posti per pura formalità
				if(posti[i].getCodice()==codice_tmp) {
					posti[i].setOccupato(true);
				}
					i++;
			}
		}
		
		result.close();
		statement.close();
		
		return;
	}

	public Spettacolo[] listaSpettacoliByCinema(String nomeCinema, ArrayList<String> listaCinema_tmp) throws SQLException {
		String query = "SELECT codice, codice_sala, nome_cinema, titolo, durata, numero_sala, data_e_ora, prezzo, tipo_3d FROM cinema_dba.mv_spettacoli_attivi WHERE nome_cinema=? ORDER BY data_e_ora";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.setString(1, nomeCinema);
		ResultSet result = preparedStatement.executeQuery();
		
		ArrayList<Spettacolo> listaSpettacoli = new ArrayList<Spettacolo>();
		while(result.next()) {
			listaSpettacoli.add(new Spettacolo(result.getInt("codice"), result.getInt("codice_sala"), result.getString("nome_cinema"), result.getString("titolo"), result.getInt("durata"), 
														result.getInt("numero_sala"), result.getString("data_e_ora"), result.getFloat("prezzo"), 
														result.getInt("tipo_3d")));
		}
		
		result = conn.createStatement().executeQuery("SELECT DISTINCT nome_cinema FROM cinema_dba.mv_spettacoli_attivi ORDER BY nome_cinema");
		while(result.next()) {
			listaCinema_tmp.add(result.getString(1));
		}
		
		result.close();
		preparedStatement.close();
		
		if(listaSpettacoli.isEmpty()) return null;
		
		return (Spettacolo[]) listaSpettacoli.toArray(new Spettacolo[listaSpettacoli.size()]);
	}
	
}
