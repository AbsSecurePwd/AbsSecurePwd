package tesina.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import tesina.data.Biglietto;
import tesina.data.Posto;
import tesina.data.Spettacolo;
import tesina.service.GeneratoreBigliettoPDF;
import tesina.service.SpettacoloService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CompraBigliettoAction extends ActionSupport {

	public String codice_spettacolo;
	
	public Spettacolo spettacolo = null;
	
	public Posto[] posti = null;
	
	public void validate() {
		if(!StringUtils.isNumeric(codice_spettacolo)) {
			addActionError("Spettacolo non trovato!");
		}
	}
	
	public String execute() {		
		SpettacoloService spettacoloService;
		
		int codice = Integer.parseInt(codice_spettacolo);
		
		try {
			spettacoloService = new SpettacoloService();
			spettacolo = spettacoloService.caricaSpettacolo(codice);
			if(spettacolo == null) {
				addActionError("Spettacolo non trovato");
				return ERROR;
			} else {
				posti = spettacoloService.getPostiSala(spettacolo.getCodice_sala());
				
				if(posti==null) return ERROR; //sala senza posti?
				
				spettacoloService.setPostiOccupati(spettacolo.getCodice(), posti);				
			}
		} catch (ClassNotFoundException|SQLException e) {
			addActionError("Si e' verificato un errore.<br>Riprovare");
			e.printStackTrace();
			return ERROR;
		} finally {
			spettacoloService = null;
		}
		
		return SUCCESS;
	}
}
