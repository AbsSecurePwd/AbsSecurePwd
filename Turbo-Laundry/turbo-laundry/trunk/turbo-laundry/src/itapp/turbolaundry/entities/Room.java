package itapp.turbolaundry.entities;

import itapp.turbolaundry.entities.interfaces.IBaseEntity;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.*;



@Entity
@Table(name = "Room")
public class Room implements Serializable, IBaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4986522703198150670L;

	@Id
	@Column(name = "ID", nullable=false, unique=true)
	private Long id;
	
	@Column(name = "Number")
	private int Number;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "FloorID", referencedColumnName="ID")
	private Floor Floor;
	
	@Column(name = "WindowsCount")
	private int WindowsCount;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "RoomID", referencedColumnName = "ID")
	private Set<LUser> LUsers;
	
	public Room() {
		
	}
	
	public Room(long id, int number, Floor floor, int windowsCount) {
		this.id = (long)id;
		this.Number = number;
		this.Floor = floor;
		this.WindowsCount = windowsCount;
	}
	
	public Room(long id, int number, int windowsCount) {
		this.id = (long)id;
		this.Number = number;
		this.WindowsCount = windowsCount;
	}
	
	@Override
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the number
	 */
	public int getNumber() {
		return this.Number;
	}
	
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.Number = number;
	}
	
	public Floor getFloor() {
		return this.Floor;
	}
	
	public void setFloor(Floor floor) {
		this.Floor = floor;
	}
	
	/**
	 * @return the windowsCount
	 */
	public int getWindowsCount() {
		return this.WindowsCount;
	}
	
	/**
	 * @param windowsCount the windowsCount to set
	 */
	public void setWindowsCount(int windowsCount) {
		this.WindowsCount = windowsCount;
	}

	public Set<LUser> getLUsers() {
		return this.LUsers;
	}
	
	public void setLUsers(Set<LUser> users) {
		this.LUsers = users;
	}
}
