/**
 * Model des Lagers.
 * Die Räume wurden als Singleton programmiert.
 * 
 * @author Henri Blankenstein
 * @version 1.00, 10.01.2015
 */

package kassandraApp.model;

import javax.persistence.Entity;

@Entity
public class Store extends Room{
	private static Store instance = null;
	
	/**
	 * Methode soll überschrieben werden.
	 */
	@Deprecated
	protected Store() {}
	
	/**
	 * Konstruktor
	 * 
	 * @param type ist der Typ des Raumes
	 */
	private Store(String type){
		super(type);
	}
	
	/**
	 * Die Methode übergibt die Instance des Raumes.
	 * 
	 * @return Instance
	 */
	public static Store getInstance(){
		if(instance == null) instance = new Store("Lager");
		return instance;
	}
}
