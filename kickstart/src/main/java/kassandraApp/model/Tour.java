package kassandraApp.model;

import javax.persistence.Entity;

import kassandraApp.model.Event;

@Entity
public class Tour extends Event{
	
	String employee = "";
	int sold = 0;
	
	@Deprecated
	protected Tour(){}
	
	public Tour(String name, long price, String employee){
		super(name, price);
		this.employee = employee;
	}
	
	public String getEmployee() {
		return employee;
	}
	
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	
	public void addAppointment(int beginYear, int beginMonth, int beginDay, int beginHour, int beginMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute, boolean premiere, boolean rehearsal) {
		super.getAppointments().add(new Appointment(beginYear, beginMonth, beginDay, beginHour, beginMinute, endYear, endMonth, endDay, endHour, endMinute));
	}

	public int getSold() {
		return sold;
	}
	
	public void setSold(int sold) {
		this.sold = sold;
	}
}
