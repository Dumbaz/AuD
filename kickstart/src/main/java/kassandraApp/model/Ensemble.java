package kassandraApp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ensemble {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Customer> actors;

	@Deprecated
	protected Ensemble() {
	}
	
	public Ensemble(String ensembleName){
		this.setName(ensembleName);
		actors = new HashSet<Customer>();
	}
	
	public Set<Customer> getActors(){
		return actors;
	}
	
	public boolean removeMember(Customer actor){
		return actors.remove(actor);
	}
	
	public boolean addMember(Customer actor){
		return actors.add(actor);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
