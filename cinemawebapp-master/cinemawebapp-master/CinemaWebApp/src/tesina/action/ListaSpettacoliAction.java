package tesina.action;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

import tesina.data.Spettacolo;
import tesina.service.SpettacoloService;

@SuppressWarnings("serial")
public class ListaSpettacoliAction extends ActionSupport {
	
	public Spettacolo[] listaSpettacoli = null;
	
	public String[] listaCinema = null;
	
	public String nomeCinema = null;
	
	public String execute() {
		
		SpettacoloService spettacoloService = null;
		ArrayList<String> listaCinema_tmp = new ArrayList<String>();
		
		try {
			spettacoloService = new SpettacoloService();
			if(nomeCinema==null || StringUtils.isBlank(nomeCinema))
				listaSpettacoli = spettacoloService.listaSpettacoli(listaCinema_tmp);
			else
				listaSpettacoli = spettacoloService.listaSpettacoliByCinema(nomeCinema, listaCinema_tmp);
			
			listaCinema = listaCinema_tmp.toArray(new String[listaCinema_tmp.size()]);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			spettacoloService = null;
		}
		
		return SUCCESS;
	}
	
}
