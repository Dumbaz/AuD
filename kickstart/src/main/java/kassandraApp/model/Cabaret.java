package kassandraApp.model;

import javax.persistence.Entity;

import kassandraApp.model.Event;

@Entity
public class Cabaret extends Event{
	
	@Deprecated
	protected Cabaret(){}
	
	public Cabaret(String name, long price, Room room){
		super(name, price);
		super.setRoom(room);
	}

	public void addAppointment(int beginYear, int beginMonth, int beginDay, int beginHour, int beginMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute, boolean premiere, boolean rehearsal) {
		super.getAppointments().add(new Appointment(beginYear, beginMonth, beginDay, beginHour, beginMinute, endYear, endMonth, endDay, endHour, endMinute, this.getSeats(), premiere, rehearsal));
	}


	public String getEmployee() { return null; }
	public void setEmployee(String employee) { }
}
