/**
 * Model der Bühne.
 * 
 * @author Henri Blankenstein
 * @version 1.00, 10.01.2015
 */

package kassandraApp.model;

import javax.persistence.Entity;

@Entity
public class Stage extends Resource{
	/**
	 * Methode soll überschrieben werden.
	 */
	@Deprecated
	protected Stage(){}
	
	/**
	 * Konstruktor
	 * 
	 * @param name ist der Name der Ressource
	 * @param room ist der Raum der Ressource
	 * @param rent ist die Miete der Ressource
	 */
	public Stage(Room room, String size, int rent){
		super(size, room, "Bühne", 0);
	}
}
