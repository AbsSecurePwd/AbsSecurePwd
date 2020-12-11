package itapp.turbolaundry.model.user;



import org.apache.struts2.ServletActionContext;

import itapp.turbolaundry.dataservices.DataService;
import itapp.turbolaundry.entities.LUser;
import itapp.turbolaundry.entities.Room;
import itapp.turbolaundry.entities.Supervisor;

public class User {

	protected DataService dataService;

	public  User (DataService ds)
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

	public LUser getUserById(long id) {
		
		LUser user = dataService.getUserById(id);
		return user;
	}

	public void createOrInsert(LUser user) {
		
		dataService.createOrEditUser(user);
		
	}
	
	public void changePasswordByLogin(String login,  String hashText)
	{
		dataService.changePasswordByLogin(login, hashText);
	}

	public void blockUser(long id, boolean block) {
		
		if(block)
		{
			dataService.blockUser(id);
		}
		else
		{
			dataService.removeBlocking(id);
		}
		
	}

	public void chargeAccount(long id, float money) {
		
		dataService.chargeAccount(id, money);
		
	}

	public void makeSupervisor(long id, int floor) {
		
		Supervisor sv = dataService.getSuperVisorByFloor(floor);	
		LUser user = dataService.getUserById(id);
		
		if(sv != null && user != null)
		{
			sv.setUser(user);
			dataService.updateSV(sv);
		}
	
		else if(user != null)
		{
			dataService.makeSupervisor(user.getLogin());
		}
		
	}

	public Room getRoomById(long room) {
		
		Room userRoom = dataService.getRoomById(room);
		return userRoom;
	}
		
	
	
}
