package itapp.turbolaundry.entities;

import itapp.turbolaundry.entities.interfaces.IBaseEntity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Supervisor")
public class Supervisor implements Serializable, IBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5915125224760586445L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", nullable=false, unique=true)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "LUserID", referencedColumnName = "ID")
	private LUser User;
	
	public Supervisor() {
		
	}
	
	public Supervisor(int id, LUser user) {
		this.id = (long)id;
		this.User = user;
	}
	
	@Override
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		id = id;
	}

	public LUser getUser() {
		return User;
	}

	public void setUser(LUser user) {
		User = user;
	}
}
