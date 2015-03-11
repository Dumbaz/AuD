package kassandraApp.model;

import javax.persistence.Entity;

@Entity
public class AudioGuide extends Tour {

	private int lent = 0;
	
	@Deprecated
	protected AudioGuide() {}
	
	public AudioGuide(String name, long price) {
		super(name, price, "");
	}
	
	public void setLentCount(int count) {
		this.lent = count;
	}
	
	public int getLentCount() {
		return lent;
	}
}
