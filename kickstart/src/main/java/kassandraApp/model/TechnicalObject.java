/**
 * Model der technischen Anlage.
 * 
 * @author Henri Blankenstein
 * @version 1.00, 10.01.2015
 */

package kassandraApp.model;

import javax.persistence.Entity;

@Entity
public class TechnicalObject extends Resource{
	/**
	 * Methode soll überschrieben werden.
	 */
	@Deprecated
	protected TechnicalObject(){}
	
	/**
	 * Konstruktor
	 * 
	 * @param name ist der Name der Ressource
	 * @param room ist der Raum der Ressource
	 * @param rent ist die Miete der Ressource
	 */
	public TechnicalObject(Room room, String name, int rent){
		super(name, room, "Technische Anlage", rent);
	}
}