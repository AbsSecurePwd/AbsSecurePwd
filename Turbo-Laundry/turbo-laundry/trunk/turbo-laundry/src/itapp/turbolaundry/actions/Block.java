package itapp.turbolaundry.actions;

import itapp.turbolaundry.model.user.User;
import itapp.turbolaundry.model.user.UserFactory;

import org.apache.struts2.ServletActionContext;

public class Block extends CommonAction{

	private static final long serialVersionUID = 1L;
	
	
	private UserFactory uFactory;

	private int id;
	private boolean block;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
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
		boolean isSuper = Boolean.parseBoolean(ServletActionContext.getRequest().getSession().getAttribute("isSuper").toString());
	
		if(!isSuper )
		{
			return "failure";
			
		}
		else 
		{
			userInfo.blockUser(id,block);
			
			return redirect("ListUsers");
		}
		
		
		
	 }

}
