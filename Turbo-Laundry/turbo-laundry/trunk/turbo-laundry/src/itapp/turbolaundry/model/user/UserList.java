package itapp.turbolaundry.model.user;

import itapp.turbolaundry.dataservices.DataService;
import itapp.turbolaundry.entities.Floor;
import itapp.turbolaundry.entities.LUser;
import itapp.turbolaundry.entities.Supervisor;

import java.util.*;

public class UserList {

	protected DataService dataService;

	public  UserList (DataService ds)
	{
		this.dataService = ds; 
	}
	
	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public  List<LUser> getUserList(int floor)
	{
		List<LUser> users = new ArrayList();
		users = dataService.getUsersByFloor(floor);
		return users;
	}
	
	public LUser getUserById(long id) {
		
		LUser user = dataService.getUserById(id);
		return user;
	}
	
	public List<Floor> getFloors()
	{
		List<Floor> floors = dataService.getAllFloors();
		return floors;
	}
	
	public Supervisor getSupervisorByFloor(int floor) {
		
		return dataService.getSuperVisorByFloor(floor);
		
	}
}
