package itapp.turbolaundry.model.user;

import itapp.turbolaundry.dataservices.DataService;
import itapp.turbolaundry.entities.LUser;
import itapp.turbolaundry.entities.*;
import java.util.*;


public class EditOrCreate 
{

	protected DataService dataService;

	public EditOrCreate (DataService ds)
	{
		this.dataService = ds; 
	}
	
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
	public  LUser getUser(String login) {
		if(!login.isEmpty())
		{
			LUser luser = dataService.getUserByLogin(login);
			return luser;
		}
		return null;
	}

	public List<Floor> getFloors()
	{
		List<Floor> floors = dataService.getAllFloors();
		return floors;
	}

	public List<Room> getRooms()
	{
		List<Room> rooms = dataService.getAllRooms();
		return rooms;
	}
}
