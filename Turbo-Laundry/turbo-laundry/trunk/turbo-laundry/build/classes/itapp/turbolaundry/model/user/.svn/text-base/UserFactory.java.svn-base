package itapp.turbolaundry.model.user;

import itapp.turbolaundry.dataservices.DataService;

public class UserFactory {
	
	protected DataService dataService;	
	
	public User getUserInfo()
	{
		User userInfo = new User(dataService);
		
		return userInfo;
	}
	public EditOrCreate getEditOrCreate()
	{
		EditOrCreate editOrCreate = new EditOrCreate(dataService);
		
		return editOrCreate;
	}
	public DataService getDataService() {
		return dataService;
	}
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	public UserList getUserList()
	{
		UserList userList = new UserList(dataService);
		
		return userList;
	}
}
