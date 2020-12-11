package itapp.turbolaundry.actions;

import java.math.BigInteger;
import java.security.MessageDigest;
import itapp.turbolaundry.actions.CommonAction;
import itapp.turbolaundry.model.user.User;
import itapp.turbolaundry.model.user.UserFactory;

public class ChangePassword extends CommonAction
{
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String user;
	
	private String newPassword;
	private String confirmedPassword;
	
	private UserFactory uFactory;
	
	private User userInfo;
	
	public UserFactory getuFactory() {
		return uFactory;
	}
	public void setuFactory(UserFactory uFactory) {
		this.uFactory = uFactory;
	}
	public User getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmedPassword() {
		return confirmedPassword;
	}
	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}
	
	public String execute()
	{
		userInfo = uFactory.getUserInfo();
		if(newPassword.equals(confirmedPassword))
		{
			
			try
			{
				MessageDigest m = MessageDigest.getInstance("MD5");
				m.reset();
			    m.update(newPassword.getBytes());
			    byte[] digest = m.digest();
			    BigInteger bigInt = new BigInteger(1,digest);
			    String hashText = bigInt.toString(16);
			    
			    while(hashText.length() < 32 )
			    {
			          hashText = "0"+hashText;
			    }
			    
			    userInfo.changePasswordByLogin(user, hashText);
			    
			    return redirect("UserInfo?userReq=" + user) ;
			}
			catch(Exception e)
			{
				addActionError("Error!Passwords was not changed."); //return error
				 
				return redirect("UserInfo?userReq=" + user);
			}
        }
		
		addActionError("Passwords are not the same"); //return error
		
		return redirect("UserInfo?userReq=" + user);
		
	}
	

}
