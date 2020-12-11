package itapp.turbolaundry.entities;

import itapp.turbolaundry.entities.interfaces.IBaseEntity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account implements Serializable, IBaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable=false, unique=true)
	private Long id;
	
	@Column(name = "Money")
	private float Money;
	
	@Column(name = "Number")
	private int Number;

	@Override
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		this.Number = number;
	}

	public float getMoney() {
		return Money;
	}

	public void setMoney(float money) {
		this.Money = money;
	}
	
	public Account() {
		
	}
	
	public Account(int id, float money, int number) {
		this.id = (long) id;
		this.Money = money;
		this.Number = number;
	}
	
	public Account(float money, int number) {
		this.Money = money;
		this.Number = number;
	}
}
