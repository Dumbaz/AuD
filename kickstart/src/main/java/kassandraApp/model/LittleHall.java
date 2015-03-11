/**
 * Model des kleinen Saals.
 * Die Räume wurden als Singleton programmiert.
 * 
 * @author Henri Blankenstein
 * @version 1.00, 10.01.2015
 */

package kassandraApp.model;

import javax.persistence.Entity;

@Entity
public class LittleHall extends Room{
	private static LittleHall instance = null;
	private int rent;

	/**
	 * Methode soll überschrieben werden.
	 */
	@Deprecated
	protected LittleHall() {}
	
	/**
	 * Konstruktor
	 * 
	 * @param rent ist die Miete des Raumes.
	 */
	private LittleHall(int rent){
		super("Kleiner Saal");
		this.setRent(rent);
	}
	
	/**
	 * Die Methode übergibt die Instance des Raumes.
	 * 
	 * @return Instance
	 */
	public static LittleHall getInstance(){
		if(instance == null) instance = new LittleHall(500);
		return instance;
	}
	
	/**
	 * Die Methode übergibt die Instance des Raumes.
	 * 
	 * @param rent ist die Miete des Raumes.
	 * @return Instance
	 */
	public static LittleHall getInstance(int rent){
		if(instance == null) instance = new LittleHall(rent);
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
