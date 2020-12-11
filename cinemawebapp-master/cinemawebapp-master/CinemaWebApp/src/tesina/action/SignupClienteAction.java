package tesina.action;

import java.sql.SQLException;

import tesina.data.User;
import tesina.service.UserService;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SignupClienteAction extends ActionSupport {
		
	public String 	username,
					password,
					nome,
					cognome,
					email;
		
	User userData = null;
	
	public void validate() {
		
		if(username==null || email==null || password==null || nome ==null || cognome==null || email==null) {
			addActionError("Assicurati di aver compilato tutti i campi obbligatori.");
		} else {
		
			if(StringUtils.isBlank(username) || username.contains(" ")) {
				addFieldError("username", "Username errato!L'username non deve essere vuoto e non puo' contenere spazi!");
			}
			
			if( (email.indexOf("@")<1) || email.lastIndexOf(".")<email.indexOf("@")+2 ) {
				addFieldError("email", "Email in formato errato!");
			}
		}
		
	}
	
	public String execute() {
		
		String return_code;
		
		userData = new User(username, password, nome, cognome, email, 0);
		
		UserService userService = null;
		try {
			userService = new UserService();
			userData = userService.signupCliente(userData);
			return_code = SUCCESS;
		} catch (SQLException e) {
			String errore = e.getMessage();
			e.printStackTrace();
			
			if(errore.contains("unique")) {
				addActionError("Username o email gia' in uso");
			} else {
				addActionError("Si e' verificato un errore durante l'elaborazione della richiesta.");
			}
			return_code = INPUT;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError("Si e' verificato un errore durante l'elaborazione della richiesta.");
			return_code = ERROR;
		} finally {
			userService = null;
		}
		
		if(return_code.equals(SUCCESS)) { //se la signup ha successo ed anche la login, allora effettuo l'accesso
			ActionContext context = ActionContext.getContext();
			context.getSession().put("userData", userData);
		}
		
		return return_code;
	}
	
}
