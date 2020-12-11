package itapp.turbolaundry.entities.calendar;

import itapp.turbolaundry.entities.Floor;
import itapp.turbolaundry.entities.interfaces.IBaseEntity;
import itapp.turbolaundry.model.calendar.DateUtility;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Day")
public class Day implements Serializable, IBaseEntity, Comparable<Day> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4988016147065744919L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", nullable=false, unique=true)
	protected long id;
	
	@Column(name = "Date")
	protected Date date;
	
	@Column(name = "IsLocked")
	protected boolean locked;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "DayID", referencedColumnName = "ID")
	private Set<Window> windows;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "FloorID", referencedColumnName="ID")
	private Floor Floor;	
	
	@Transient
	private boolean current;
	
	@Transient
	// To mark days in past and not active in future
	private boolean active;
	
	@Transient
	private boolean yesterday;
	
	@Transient
	private int bookedWinNum;

	Day() {
		current = false;
		active = true;		
	}
	
	public Day(Date date, boolean locked, Set<Window> windows) {
		this();
		this.date = date;
		this.locked = locked;
		this.windows = windows;
	}
	
	public Day(Date date,boolean active, boolean locked, Set<Window> windows) {
		this();
		this.date = date;
		this.locked = locked;
		this.windows = windows;
		this.active = active;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Window> getWindows() {
		return windows;
	}

	public void setWindows(Set<Window> windows) {
		this.windows = windows;
	}

	public boolean isCurrent() {
		Date today = DateUtility.getCurrentDate();
		if(DateUtility.compareDates(date,today)==true){			
			return true;		
		}
		return false;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public long getId() {
		return this.id;
	}
	
	public Floor getFloor() {
		return this.Floor;
	}
	
	public void setFloor(Floor floor) {
		this.Floor = floor;
	}
	
	public boolean isYesterday() {
		Date yesterday = DateUtility.getYesterday(DateUtility.getCurrentDate());
		if(DateUtility.compareDates(date,yesterday)==true){			
			return true;		
		}
		return false;
	}

	public void setYesterday(boolean yesterday) {
		this.yesterday = yesterday;
	}

	@Override
	public int compareTo(Day o) {		
		return DateUtility.compareTo(this.date, o.getDate());
	}
	
	public int getBookedWinNum() {
		int num = 0;
		for(Window win : windows){
			if(win.getUser() != null){
				num++;
			}
		}
		return num;
	}
}