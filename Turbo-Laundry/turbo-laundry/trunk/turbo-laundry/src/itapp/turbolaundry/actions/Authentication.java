package itapp.turbolaundry.actions;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import itapp.turbolaundry.dataservices.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

/**
 * @author Marcin
 *
 */


public class Authentication extends CommonAction implements ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataService dataService;
	
	private String login;
	private String password;
	private String  hashText; //string for holding hashed pass
	
	private HttpServletRequest request;
		
	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String credentialsCheck(){
		
		
		/*Create MD5 from string input*/
		try {
			String password = this.getPassword();
		    MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
	       // m.update(this.getPassword().getBytes());
			m.update(password.getBytes());
	        byte[] digest = m.digest();
	        BigInteger bigInt = new BigInteger(1,digest);
	        hashText = bigInt.toString(16);
	        while(hashText.length() < 32 ){
	          hashText = "0"+hashText;
	        }	       
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Create MD5 from string input*/
		
		String pass = dataService.getUserPassByLogin(this.getLogin()); //return password from db
		
		if(pass.compareTo(hashText) == 0){  //check if passwords are the same
			HttpSession session = request.getSession();
			session.setAttribute("authorized","yes"); //set session attr "authorized" to "yes"
			session.setAttribute("userLogin", this.getLogin());
			long userId = dataService.getUserIdByLogin(this.getLogin());
			session.setAttribute("userId", userId);
			//TODO: Set proper attributes from db
			boolean isAdmin = dataService.isAdmin(userId);			
			session.setAttribute("isAdmin", isAdmin);
			boolean isSuper = dataService.isSuper(userId);
			session.setAttribute("isSuper", isSuper);
			boolean isBlocked = dataService.isUserBlocked(userId);
			session.setAttribute("isBlocked", isBlocked);
			session.setAttribute("message", "");
			return SUCCESS;			
		}else{
			addActionError("Please enter valid credentials"); //return error
			return "failure";
		}
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	/**
	 * @return the dataService
	 */
	public DataService getDataService() {
		return dataService;
	}
	/**
	 * @param dataService the dataService to set
	 */
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	

	/**
	 * @return the hashText
	 */
	public String getHashText() {
		return hashText;
	}
	/**
	 * @param hashText the hashText to set
	 */
	public void setHashText(String hashText) {
		this.hashText = hashText;
	}
	
}
