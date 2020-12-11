package itapp.turbolaundry.actions;

import org.apache.struts2.ServletActionContext;

import itapp.turbolaundry.actions.CommonAction;
import itapp.turbolaundry.entities.LUser;
import itapp.turbolaundry.model.user.*;


public class UserInfo extends CommonAction{
	
	private static final long serialVersionUID = 1L;
	
	private UserFactory uFactory;

	private boolean isAdmin;
	
	
	private User userInfo;
	private LUser user;
	private String userReq;	// url parameter


	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public UserFactory getuFactory() {
		return uFactory;
	}

	public void setuFactory(UserFactory uFactory) {
		this.uFactory = uFactory;
	}
	public String getUserReq() {
		return userReq;
	}

	public void setUserReq(String userReq) {
		this.userReq = userReq;
	}

	public LUser getUser() {
		return user;
	}

	public void setUser(LUser user) {
		this.user = user;
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
		boolean isSuper = Boolean.parseBoolean(ServletActionContext.getRequest().getSession().getAttribute("isSuper").toString());
		isAdmin = Boolean.parseBoolean(ServletActionContext.getRequest().getSession().getAttribute("isAdmin").toString());
		String login = ServletActionContext.getRequest().getSession().getAttribute("userLogin").toString();
		
		
		if(!isSuper && !isAdmin)
		{
			user = userInfo.getUser(login);
			
		}
		else if(!isAdmin)
		{
			if(userReq == null || userReq.isEmpty())
			{
				user = userInfo.getUser(login);
			}
			else
			{
				LUser loggedUser = userInfo.getUser(login);
				user = userInfo.getUser(userReq);
				
				if(user != null && (loggedUser.getRoom().getFloor().getFloorNumber() != user.getRoom().getFloor().getFloorNumber()))
				{
					return "failure";
				}
			}
		}
		else
		{
			if(userReq == null || userReq.isEmpty())
			{
				return redirect("AdminListUsers");
			}
			else
			{
				user = userInfo.getUser(userReq);
			}
		}
		
		if(user != null)
		{
			return "success";
		}
		return "error";
		

	 }
}
