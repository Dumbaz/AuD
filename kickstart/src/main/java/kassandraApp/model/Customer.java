package kassandraApp.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.salespointframework.catalog.Product;
import org.salespointframework.useraccount.UserAccount;

//Muss bei jeder Klasse dazu. Wichtig für die DB
@Entity
public class Customer {
	
	// Falls man die Id nicht selber setzen will, kann die mit @GeneratedValue vom JPA-Provider generiert und gesetzt
	// werden
	@Id
	@GeneratedValue
	private Long id;
	
	//der UserAccount hat keine Funktion für das Gehalt, muss also erst erstellt werden
	private Integer pay; 
	
	// Jedem Customer ist genau ein UserAccount zugeordnet (um später über den UserAccount an den Customer zu kommen)
	@OneToOne
	private UserAccount userAccount;
	
	private boolean expired = false;
	private long creation;
	private long deletion;
	
	
	@Deprecated //Compiler warnt einen, wenn das Programm versucht etwas zu überschreiben
	protected Customer() {
	}

	public Customer(UserAccount userAccount, int pay) {
		this.userAccount = userAccount;
		this.setPay(pay);
		creation = Calendar.getInstance().getTimeInMillis();
	}
	public Customer(UserAccount userAccount) {
		this.userAccount = userAccount;
		creation = Calendar.getInstance().getTimeInMillis();
	}
	
	public Integer getPay() {
		return pay;
	}
	
	public void setPay(int pay) {
		if(pay < 0) {
			throw new IllegalArgumentException();
		}
		this.pay = pay;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object == null) {
			return false;
		}
		if(object instanceof Customer) {
			return ((Customer)object).id.equals(this.id);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	public void setExpired() {
		expired = true;
		deletion = Calendar.getInstance().getTimeInMillis();
	}
	
	public boolean getExpired() {
		return expired;
	}
	
	public void setCreation(long creation) {
		this.creation = creation;
	}
	
	public long getCreation() {
		return creation;
	}

	public void setDeletion(long deletion) {
		this.deletion = deletion;
	}
	
	public long getDeletion() {
		return deletion;
	}
}
