/**
 * Model der Ressourcen
 * 
 * @author Henri Blankenstein
 * @version 1.00, 10.01.2015
 */

package kassandraApp.model;

import static org.joda.money.CurrencyUnit.EUR;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;

import org.joda.money.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.quantity.Units;

@Entity
public class Resource extends Product{
	private Room room;
	private String type;
	private int rent;
	private boolean state; //true = extern, false = intern
	private boolean expired = false;
	private long creation;
	private long deletion;
	
	/**
	 * Methode soll überschrieben werden.
	 */
	@Deprecated
	protected Resource(){}
	
	/**
	 * Konstruktor
	 * 
	 * @param name ist der Name der Ressource
	 * @param room ist der Raum der Ressource
	 * @param type ist der Typ der Ressource
	 * @param rent ist die Miete der Ressource
	 */
	public Resource(String name, Room room, String type, int rent){
		super(name, Money.of(EUR, 0.00), Units.METRIC);
		setRoom(room);
		this.type = type;
		setRent(rent);
		creation = Calendar.getInstance().getTimeInMillis();
	}
	
	/**
	 * Die Methode übergibt den Raum
	 * 
	 * @return Raum
	 */
	public Room getRoom() {
		return room;
	}
	
	/**
	 * Die Methode ändert den Raum
	 * 
	 * @param room Raumname
	 */
	public void setRoom(Room room) {
		this.room = room;
	}
	
	/**
	 * Die Methode übergibt den Typen
	 * 
	 * @return Typ
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Die Methode übergibt die Miete
	 * 
	 * @return Miete
	 */
	public int getRent() {
		return rent;
	}

	/**
	 * Die Methode ändert die Miete
	 * 
	 * @param rent Miete
	 */
	public void setRent(int rent) {
		if(rent < 0) throw new IllegalArgumentException();
		this.rent = rent;
		if(rent > 0) setState(true);
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
	
	/**
	 * Die Methode übergibt den Status, ob die Ressource extern oder intern ist. 0 = Intern, 1 = Extern.
	 * 
	 * @return Status
	 */
	public boolean getState() {
		return state;
	}

	/**
	 * Die Methode ändert den Status
	 * 
	 * @param state Status
	 */
	public void setState(boolean state) {
		this.state = state;
	}
}
