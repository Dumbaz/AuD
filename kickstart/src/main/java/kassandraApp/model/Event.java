package kassandraApp.model;

import static org.joda.money.CurrencyUnit.EUR;










import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.joda.money.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.quantity.Units;

@Entity
public abstract class Event extends Product {
	
	@Column(name="appointments", length=10000000)
	private Vector<Appointment> appointments = new Vector<Appointment>();
	
	private Room room = null;
	private String seats = null;
	private boolean expired = false;
	
	@Deprecated
	protected Event(){}
	
	public Event(String name, long price){
		super(name, Money.of(EUR, price), Units.METRIC);
	}
	
	public void setAppointments(Vector<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	public Vector<Appointment> getAppointments() {
		return appointments;
	}
	
	public String toUrl() {
		String r =  this.getName().replaceAll("\\s","");
		r = r.toLowerCase();
		r = r.replace("ä","ae");
		r = r.replace("ü","ue");
		r = r.replace("ö","oe");
		return r;
	}
	
	public void setRoom(Room room) {
		this.room = room;
		if(room == GreatHall.getInstance()) {
			String s = "1111111111111111111111111"
					 + "1111111111111111111111111"
					 + "1111111111111111111111111"
					 + "1111111111111111111111111"
					 + "1111111111111111111111111"
					 + "1111111111111111111111111"
					 + "1111111111111111111111111"
					 + "1111111111111111111111111"
					 + "1111111111111111111111111"
					 + "1111111111111111111111111";	
			this.seats = s;
		}
		else {
			String s = "1111111111111111111111111"
					 + "1111111111111111111111111"
					 + "1111111111111111111111111"
					 + "1111111111111111111111111";
			this.seats = s;
		}
	}
	
	public String getRoom() {
		if (this.room.getType().equals("Großer Saal")) {
			return "Großer Saal";
		}
		else {return "Kleiner Saal";}
	}
	
	public String getSeats() {
		return this.seats;
	}
	
	public void setExpired() {
		expired = true;
	}
	
	public boolean getExpired() {
		return expired;
	}
	
	public boolean sellable() {
		for (Appointment a : this.getAppointments()) {
			if (a.getRehearsal() == false) return true;
		}
		return false;
	}
	
	public abstract void addAppointment(int beginYear, int beginMonth, int beginDay, int beginHour, int beginMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute, boolean premiere, boolean rehearsal);
	public abstract String getEmployee();
	public abstract void setEmployee(String employee);
}