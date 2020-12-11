package itapp.turbolaundry.actions;

import itapp.turbolaundry.entities.LUser;
import itapp.turbolaundry.model.user.UserFactory;
import itapp.turbolaundry.model.user.EditOrCreate;
import java.util.*;

import itapp.turbolaundry.entities.*;

public class Create extends CommonAction{

	private static final long serialVersionUID = 1L;
	
	private UserFactory uFactory;

	private EditOrCreate createUser;
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
		 
		createUser = uFactory.getEditOrCreate();
		 
		floors = createUser.getFloors();
		rooms = createUser.getRooms();
		
		if(floors != null && rooms!= null)
		{
			return "success";
		}
		return "error";

	 }

	public EditOrCreate geCreateUser() {
		return createUser;
	}

	public void setCreateUser(EditOrCreate createUser) {
		this.createUser = createUser;
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

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}
