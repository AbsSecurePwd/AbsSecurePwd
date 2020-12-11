package itapp.turbolaundry.entities;

import itapp.turbolaundry.entities.interfaces.IBaseEntity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "Blocked")
public class Blocked implements Serializable, IBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "LUserID")
	private long UserId;
	
	@Column(name = "Date")
	private Date Date;
	
	public long getUserId() {
		return this.UserId;
	}
	
	public void setUserId(long userId) {
		this.UserId = userId;
	}
	
	public Date getDate() {
		return this.Date;
	}
	
	public void setDate(Date date) {
		this.Date = date;
	}
	
	public Blocked() {
		
	}
	
	public Blocked(long id, Date date) {
		this.UserId = (long)id;
		this.Date = date;
	}

	@Override
	public long getId() {
		return this.getUserId();
	}

	public void setId(long id) {
		this.setUserId(id);
	}
}
