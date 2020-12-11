package itapp.turbolaundry.actions;

import itapp.turbolaundry.entities.LUser;
import itapp.turbolaundry.model.user.User;
import itapp.turbolaundry.model.user.UserFactory;

import org.apache.struts2.ServletActionContext;

public class ChargeAccount extends CommonAction{

	private static final long serialVersionUID = 1L;

	private UserFactory uFactory;

	private String charge;
	private long id;
	
	private User userInfo;

	public UserFactory getuFactory() {
		return uFactory;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	// all struts logic here
	 public String execute()
	 {
		userInfo = uFactory.getUserInfo();
		boolean isAdmin = Boolean.parseBoolean(ServletActionContext.getRequest().getSession().getAttribute("isAdmin").toString());
	
		if(!isAdmin )
		{
			return "failure";
			
		}
		else 
		{
			float money = Float.parseFloat(charge);
			
			userInfo.chargeAccount(id,money);
			
			LUser user = userInfo.getUserById(id);
			
			return redirect("UserInfo?login=" + user.getLogin());
		}
		
	 }

}
