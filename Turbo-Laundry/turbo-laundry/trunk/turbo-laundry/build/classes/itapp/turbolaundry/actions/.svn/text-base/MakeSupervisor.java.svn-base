package itapp.turbolaundry.actions;

import itapp.turbolaundry.model.user.User;
import itapp.turbolaundry.model.user.UserFactory;

import org.apache.struts2.ServletActionContext;

public class MakeSupervisor extends CommonAction{

	private static final long serialVersionUID = 1L;

	private UserFactory uFactory;

	private long id;
	private int floor;
	
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public void setId(long id) {
		this.id = id;
	}


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
			userInfo.makeSupervisor(id, floor);
			
			return redirect("AdminListUsers?floor=" + floor);
		}
		
		
		
	 }
}
