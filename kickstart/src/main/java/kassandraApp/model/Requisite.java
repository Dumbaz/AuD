/**
 * Model der Requisiten
 * 
 * @author Henri Blankenstein
 * @version 1.00, 10.01.2015
 */

package kassandraApp.model;

import javax.persistence.Entity;

@Entity
public class Requisite extends Resource{
	/**
	 * Methode soll überschrieben werden.
	 */
	@Deprecated
	protected Requisite(){}
	
	/**
	 * Konstruktor
	 * 
	 * @param name ist der Name der Ressource
	 * @param room ist der Raum der Ressource
	 * @param rent ist die Miete der Ressource
	 */
	public Requisite(Room room, String name, int rent){
		super(name, room, "Requisite", rent);
	}
}