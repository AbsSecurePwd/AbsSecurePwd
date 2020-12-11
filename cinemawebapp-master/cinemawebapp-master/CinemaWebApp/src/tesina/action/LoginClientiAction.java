package tesina.action;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import tesina.utils.MD5Encoder;
import tesina.data.User;
import tesina.service.UserService;

@SuppressWarnings("serial")
public class LoginClientiAction extends ActionSupport {
	
	public String username;
	private String password;
	
	private User userData;
	
	//per redirect ad action precedentemente richiesta
	private String url;
	
	public String login() {
		
		//controllo che l'action sia chiamata dal form!
		if(username==null) {
			addActionError("E' necessario compilare i campi richiesti per poter procedere!");
			return INPUT;
		}
		
		try {
			UserService userService = new UserService();
			userData = userService.loginCliente(username, password);
		} catch (ClassNotFoundException | SQLException e) {
			addActionError("Si e' verificato un errore!");
			return INPUT;
		}
		
		//se l'User letto � null, non esiste alcun utente con questa combinazione di userId/password
		if(userData==null) {
			addActionError("Login fallito. Username e/o password errati!");
			return INPUT;
		}
		
		//inserisco l'User nella Session
		ActionContext context = ActionContext.getContext();
		context.getSession().put("userData", userData);
		
		return SUCCESS;
	}
	
	public String logout() {
		//elimino l'User dalla Session
		ActionContext context = ActionContext.getContext();
		context.getSession().remove("userData");
		return SUCCESS;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		//Calcolo MD5
		MD5Encoder md5econder = new MD5Encoder();
		password = md5econder.encodeString(password);
		this.password = password;
	}

	public User getUserData() {
		return userData;
	}

	public void setUserData(User userData) {
		this.userData = userData;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if(url.contains("loginForm")) {
			this.url = "/home";
		} else {
			this.url = url;
		}
	}	
}
