package itapp.turbolaundry.actions;

import itapp.turbolaundry.actions.CommonAction;
import itapp.turbolaundry.entities.*;
import itapp.turbolaundry.model.user.UserFactory;
import itapp.turbolaundry.model.user.UserList;

import java.util.*;

import org.apache.struts2.ServletActionContext;
public class AdminListUsers extends CommonAction{
	
	private static final long serialVersionUID = 1L;
	
	private UserFactory uFactory;
	private List<LUser> users;
	private UserList userList;
	private Supervisor sv;
	
	public Supervisor getSv() {
		return sv;
	}

	public void setSv(Supervisor sv) {
		this.sv = sv;
	}

	private int floor = 1;
	
	private List<Floor> floors;
	
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
		boolean isAdmin = Boolean.parseBoolean(ServletActionContext.getRequest().getSession().getAttribute("isAdmin").toString());
		if(isAdmin)
		{
			userList = uFactory.getUserList();	
			users = userList.getUserList(floor);
			floors = userList.getFloors();
			sv = userList.getSupervisorByFloor(floor);
			
			if(users != null)
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

	public List<Floor> getFloors() {
		return floors;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}
	

}
