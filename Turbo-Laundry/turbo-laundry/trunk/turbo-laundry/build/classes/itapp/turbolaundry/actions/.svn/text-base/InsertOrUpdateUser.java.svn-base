package itapp.turbolaundry.actions;

import itapp.turbolaundry.entities.Account;
import itapp.turbolaundry.entities.Floor;
import itapp.turbolaundry.entities.LUser;
import itapp.turbolaundry.entities.Room;
import itapp.turbolaundry.model.user.User;
import itapp.turbolaundry.model.user.UserFactory;


import org.apache.struts2.ServletActionContext;

public class InsertOrUpdateUser extends CommonAction{

	private static final long serialVersionUID = 1L;

	private UserFactory uFactory;

	private User userInfo;
	private LUser user;

	
	
	private int id;
	private String login;
	private String firstName;
	private String lastName;
	private long room;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getRoom() {
		return room;
	}

	public void setRoom(long room) {
		this.room = room;
	}
	

	private String userReq;	// url parameter

	public UserFactory getuFactory() {
		return uFactory;
	}

	public void setuFactory(UserFactory uFactory) {
		this.uFactory = uFactory;
	}
	public String getUserReq() {
		return userReq;
	}

	public void setUserReq(String userReq) {
		this.userReq = userReq;
	}

	public LUser getUser() {
		return user;
	}

	public void setUser(LUser user) {
		this.user = user;
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
		//boolean isAdmin = Boolean.parseBoolean(ServletActionContext.getRequest().getSession().getAttribute("isAdmin").toString());
		boolean isAdmin = true;
		
		Room userRoom = userInfo.getRoomById(room);
		
		if(isAdmin)
		{
			if(id == 0)
			{
				LUser newUser = new LUser();
				
				newUser.setLogin(login);
				newUser.setFirstName(firstName);
				newUser.setLastName(lastName);
				newUser.setRoom(userRoom);

				//newUser.setPassword(password);
				userInfo.createOrInsert(newUser);
				
			}
			else
			{
				user = userInfo.getUserById(id);
				user.setLogin(login);
				user.setFirstName(firstName);
				user.setLastName(lastName);
			    user.setRoom(userRoom);
				userInfo.createOrInsert(user);
			}
			
			return redirect("UserInfo?userReq=" + login);
		}
		else
		{
			return "failure";
	
		}


	 }
	
}
