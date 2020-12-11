package tesina.action;


import java.util.Map;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import tesina.data.Spettacolo;
import tesina.data.User;
import tesina.service.BigliettoService;
import tesina.service.SpettacoloService;


@SuppressWarnings("serial")
public class BigliettoAction extends ActionSupport {
			
	public int codice_spettacolo;
	
	public int posto[];
	
	public int tipo;
	
	public String email;
	
	public User userData;
	public Spettacolo spettacolo;
	
	public int codice_prenotazione;
	
	public void validate() {
		if( (email.indexOf("@")<1) || email.lastIndexOf(".")<email.indexOf("@")+2 ) {
			addFieldError("email", "Email in formato errato!");
		}
		if(posto==null || posto.length==0)
			addActionError("E' necessario selezionare almeno 1 posto!");
	}
	
	//per redirect ad action precedentemente richiesta
	public String execute() {
		
		String return_code = SUCCESS;
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		User userData = (User)session.get("userData");
		
		BigliettoService bigliettoService = null;
		SpettacoloService spettacoloService = null;
		
		try {
			spettacoloService = new SpettacoloService();
			spettacolo = spettacoloService.caricaSpettacolo(codice_spettacolo);
			if(spettacolo == null) {
				addActionError("Spettacolo non trovato");
				return ERROR;
			}
			bigliettoService = new BigliettoService(spettacolo, userData);
			if(userData==null) { //caso Guest
				userData = new User(email); //guest
				bigliettoService.setUserData(userData);
				codice_prenotazione = bigliettoService.acquistaBigliettiGuest(posto, tipo);
			} else { //caso utente registrato
				email = userData.getEmail();
				codice_prenotazione = bigliettoService.acquistaBigliettiCliente(posto, tipo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		} finally {
			bigliettoService = null;
		}
		
		return SUCCESS;
	}
}
