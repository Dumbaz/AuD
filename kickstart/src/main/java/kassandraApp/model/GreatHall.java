/**
 * Model des großen Saals.
 * Die Räume wurden als Singleton programmiert.
 * 
 * @author Henri Blankenstein
 * @version 1.00, 10.01.2015
 */

package kassandraApp.model;

import javax.persistence.Entity;

@Entity
public class GreatHall extends Room{
	private static GreatHall instance = null;
	private int rent;
	
	/**
	 * Methode soll überschrieben werden.
	 */
	@Deprecated
	protected GreatHall() {}

	/**
	 * Konstruktor
	 * 
	 * @param rent ist die Miete des Raumes.
	 */
	private GreatHall(int rent){
		super("Großer Saal");
		this.setRent(rent);
	}
	
	/**
	 * Die Methode übergibt die Instance des Raumes.
	 * 
	 * @return Instance
	 */
	public static GreatHall getInstance(){
		if(instance == null) instance = new GreatHall(1000);
		return instance;
	}
	
	/**
	 * Die Methode übergibt die Instance des Raumes.
	 * 
	 * @param rent ist die Miete des Raumes.
	 * @return Instance
	 */
	public static GreatHall getInstance(int rent){
		if(instance == null) instance = new GreatHall(rent);
		return instance;
	}

	/**
	 * Die Methode übergibt die Miete des Raumes.
	 * 
	 * @return Miete
	 */
	public int getRent() {
		return rent;
	}

	/**
	 * Die Methode ändern die Miete.
	 * 
	 * @param rent ist die Miete des Raumes.
	 */
	public void setRent(int rent) {
		if(rent < 0) throw new IllegalArgumentException();
		this.rent = rent;
	}
}

