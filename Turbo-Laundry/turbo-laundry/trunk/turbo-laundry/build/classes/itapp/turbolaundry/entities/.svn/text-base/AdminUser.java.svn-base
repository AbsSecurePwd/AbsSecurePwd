package itapp.turbolaundry.entities;

import itapp.turbolaundry.entities.interfaces.IBaseEntity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "AdminUser")
public class AdminUser implements Serializable, IBaseEntity {
        
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
		@Column(name = "ID", nullable=false, unique=true)
        private long id;
        
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "LUserID", referencedColumnName = "ID")
        private LUser user;
        
        public LUser getLUserId() {
                return this.user;
        }
        
        public void setLUserId(LUser userId) {
                this.user = userId;
        }

        @Override
		public long getId() {
			return this.id;
		}

		public void setId(long id) {
			this.id = id;
		}
}