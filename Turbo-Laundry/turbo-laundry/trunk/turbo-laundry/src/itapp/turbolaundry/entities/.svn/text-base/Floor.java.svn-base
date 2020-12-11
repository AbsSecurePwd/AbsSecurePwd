package itapp.turbolaundry.entities;

import itapp.turbolaundry.entities.interfaces.IBaseEntity;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Floor")
public class Floor implements Serializable, IBaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable=false, unique=true)
	protected long id;
	
	@Column(name = "FloorNo")
	protected int floorNumber;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "FloorID", referencedColumnName = "ID")
	private Set<Room> Rooms;
	
	public Floor() {		
	}
	
	public Floor(long id, int floorNo) {
		this.id = id;
		this.floorNumber = floorNo;
	}
	
	public int getFloorNumber() {
		return this.floorNumber;
	}
	
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	@Override
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Set<Room> getRooms() {
		return this.Rooms;
	}
	
	public void setRooms(Set<Room> rooms) {
		this.Rooms = rooms;
	}
}