package itapp.turbolaundry.entities.calendar;

import itapp.turbolaundry.entities.LUser;
import itapp.turbolaundry.entities.interfaces.IBaseEntity;
import itapp.turbolaundry.model.calendar.DateUtility;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Window")
public class Window implements Serializable, IBaseEntity, Comparable<Window> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4859107281306702544L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "WindowNumber")
	private int windowNumber;
	
	@Column(name = "IsLocked")
	private boolean isLocked;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "LUserID", referencedColumnName = "ID")
	private LUser user;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "DayID", referencedColumnName = "ID")
	private Day day;
	
	@Transient
	private int roomNumber;	
	
	@Transient
	private boolean active;
	
	@Transient
	private String wtime;
	
	
	public Window() {
		this.active = true;		
	}
	
	public Window(int windowNumber, boolean isLocked, LUser user, Day day) {
		this();
		this.windowNumber = windowNumber;
		this.isLocked = isLocked;
		this.user = user;
		this.day = day;
	}
	
	@Override
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public int getWindowNumber() {
		return this.windowNumber;
	}

	public void setWindowNumber(int windowNumber) {
		this.windowNumber = windowNumber;
	}

	public boolean getIsLocked() {
		return this.isLocked;
	}

	public void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public LUser getUser() {
		return this.user;
	}

	public void setUser(LUser user) {
		this.user = user;
	}
	
	public Day getDay() {
		return this.day;
	}
	
	public void setDay(Day day) {
		this.day = day;
	}

	public int getRoomNumber() {
		if(user != null){
			return user.getRoom().getNumber();
		}
		return -1;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getWtime() {		
		return DateUtility.numberToTime(windowNumber);
	}

	public void setWtime(String wtime) {
		this.wtime = wtime;
	}

	@Override
	public int compareTo(Window o) {
		Integer a = this.windowNumber;
		Integer b = o.getWindowNumber();				
		return a.compareTo(b);
	}	
			
}
