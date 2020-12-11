package itapp.turbolaundry.entities;
import itapp.turbolaundry.entities.calendar.Window;
import itapp.turbolaundry.entities.interfaces.IBaseEntity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "LUser")
public class LUser implements Serializable, IBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3447014870762867887L;

	@Id
	@Column(name = "ID", nullable=false, unique=true)
	private long id;
	
	@Column(name = "Password")
	private String Password;
	
	@Column(name = "FirstName")
	private String FirstName;
	
	@Column(name = "LastName")
	private String LastName;
	
	@Column(name = "Login")
	private String Login;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "RoomID", referencedColumnName="ID")
	private Room Room;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "AccountID", referencedColumnName="ID")
	private Account Account;
	
	@OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID", referencedColumnName = "LUserID")
	private Blocked Blocked;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "LUserID", referencedColumnName = "ID")
	private Set<Window> Windows;
	
	public LUser(long id,
			String password,
			String firstName,
			String lastName,
			String login,
			Room room,
			Account account,
			Blocked blocked,
			Set<Window> windows) {
		this.id = (long)id;
		this.Password = password;
		this.FirstName = firstName;
		this.LastName = lastName;
		this.Login = login;
		this.Room = room;
		this.Account = account;
		this.Blocked = blocked;
		this.Windows = windows;
	}
	
	public LUser(String password,
			String firstName,
			String lastName,
			String login,
			Room room,
			Account account,
			Blocked blocked,
			Set<Window> windows) {
		this.Password = password;
		this.FirstName = firstName;
		this.LastName = lastName;
		this.Login = login;
		this.Room = room;
		this.Account = account;
		this.Blocked = blocked;
		this.Windows = windows;
	}
	
	public LUser() {
		
	}
	
	@Override
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getPassword() {
		return this.Password;
	}
	
	public void setPassword(String password) {
		this.Password = password;
	}
	
	public String getFirstName() {
		return this.FirstName;
	}
	
	public void setFirstName(String firstName) {
		this.FirstName = firstName;
	}
	
	public String getLastName() {
		return this.LastName;
	}
	
	public void setLastName(String lastName) {
		this.LastName = lastName;
	}
	
	public String getLogin() {
		return this.Login;
	}
	
	public void setLogin(String login) {
		this.Login = login;
	}

	public Account getAccount() {
		return Account;
	}

	public void setAccount(Account account) {
		Account = account;
	}

	public Blocked getBlocked() {
		return Blocked;
	}

	public void setBlocked(Blocked blocked) {
		Blocked = blocked;
	}

	public Room getRoom() {
		return Room;
	}

	public void setRoom(Room room) {
		Room = room;
	}

	public Set<Window> getWindows() {
		return Windows;
	}

	public void setWindows(Set<Window> windows) {
		Windows = windows;
	}
	
}
