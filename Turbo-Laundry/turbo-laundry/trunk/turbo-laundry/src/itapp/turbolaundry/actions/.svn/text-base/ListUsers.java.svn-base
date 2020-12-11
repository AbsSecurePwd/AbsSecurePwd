package itapp.turbolaundry.actions;

import itapp.turbolaundry.actions.CommonAction;
import itapp.turbolaundry.entities.*;
import itapp.turbolaundry.model.user.UserFactory;
import itapp.turbolaundry.model.user.UserList;

import java.util.*;

import org.apache.struts2.ServletActionContext;
public class ListUsers extends CommonAction{
	
	private static final long serialVersionUID = 1L;
	
	private UserFactory uFactory;
	private List<LUser> users;
	private UserList userList;
	
	private int floor;
	
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public UserFactory getuFactory() {
		return uFactory;
	}

	public void setuFactory(UserFactory uFactory) {
		this.uFactory = uFactory;
	}

	public List<LUser> getUsers() {
		return users;
	}

	public UserList getUserList() {
		return userList;
	}

	public void setUserList(UserList userList) {
		this.userList = userList;
	}

	public void setUsers(List<LUser> users) {
		this.users = users;
	}
	
	public String execute()
	{
		boolean isSV = Boolean.parseBoolean(ServletActionContext.getRequest().getSession().getAttribute("isSuper").toString());

		long id = (Long) ServletActionContext.getRequest().getSession().getAttribute("userId");
		
		if(isSV)
		{
			userList = uFactory.getUserList();	
			LUser user = userList.getUserById(id);
			
			int floor = user.getRoom().getFloor().getFloorNumber();
			
			users = userList.getUserList(floor);

			if(users != null && users.size() > 0)
			{
				return "success";
			}
			else
			{
				return "error";
			}
		}
		else
		{
			return "failure";
		}

	}
	

}
