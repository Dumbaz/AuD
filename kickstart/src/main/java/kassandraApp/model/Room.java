/**
 * Model des Raums.
 * 
 * @author Henri Blankenstein
 * @version 1.00, 10.01.2015
 */

package kassandraApp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROOMS")
public abstract class Room implements Serializable{
	@Id @GeneratedValue private long id;
	private String type;
	
	/**
	 * Methode soll überschrieben werden.
	 */
	@Deprecated
	protected Room() {}
	
	/**
	 * Konstruktor
	 * 
	 * @param type ist der Typ des Raumes
	 */
	public Room(String type){
		this.type = type;
	}
	
	/**
	 * Die Methode übergibt die ID des Raumes.
	 * 
	 * @return ID
	 */
	@Column(name="room_id", nullable=false) 
	public long getRoomID(){
	        return id;
	 }
	
	/**
	 * Die Methode übegibt den Typen des Raumes.
	 * 
	 * @return Typ
	 */
	public String getType(){
		return type;
	}
}
