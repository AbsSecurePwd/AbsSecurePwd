package itapp.turbolaundry.actions;

import itapp.turbolaundry.entities.LUser;
import itapp.turbolaundry.model.user.UserFactory;
import itapp.turbolaundry.model.user.EditOrCreate;
import java.util.*;

import itapp.turbolaundry.entities.*;

public class Edit extends CommonAction{

	private static final long serialVersionUID = 1L;
	
	private UserFactory uFactory;

	private EditOrCreate editUser;
	private LUser user;

	private String userReq;	// url parameter
	
	private boolean admin = true;
	
	private List<Floor> floors;
	
	private List<Room> rooms;
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public LUser getUser() {
		return user;
	}

	public void setUser(LUser user) {
		this.user = user;
	}
	public UserFactory getuFactory() {
		return uFactory;
	}

	public void setuFactory(UserFactory uFactory) {
		this.uFactory = uFactory;
	}


	// all struts logic here
	 public String execute()
	 {
		//TODO validation if logged user have access to this data. 
		 
		editUser = uFactory.getEditOrCreate();
		 
		if(userReq != null && !userReq.isEmpty())
		{
			user = editUser.getUser(userReq);
		}
		
		floors = editUser.getFloors();
		rooms = editUser.getRooms();
		
		if(user != null && floors != null && rooms != null)
		{
			return "success";
		}
		return "error";

	 }

	public EditOrCreate getEditUser() {
		return editUser;
	}

	public void setEditUser(EditOrCreate editUser) {
		this.editUser = editUser;
	}

	public String getUserReq() {
		return userReq;
	}

	public void setUserReq(String userReq) {
		this.userReq = userReq;
	}

	public List<Floor> getFloors() {
		return floors;
	}

	public void setFloor(List<Floor> floors) {
		this.floors = floors;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRoom(List<Room> rooms) {
		this.rooms = rooms;
	}
}
