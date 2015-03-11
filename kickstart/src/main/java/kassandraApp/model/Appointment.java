package kassandraApp.model;

import java.io.Serializable;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Appointment implements Serializable {
	
	private Calendar begin = new GregorianCalendar();
	private Calendar end = new GregorianCalendar();
	
	private String seats = "";
	private boolean premiere = false; 
	private boolean rehearsal;
	
	
    Appointment(int beginYear, int beginMonth, int beginDay, int beginHour, int beginMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute) {
		this.begin.set(beginYear, beginMonth, beginDay, beginHour, beginMinute);
		this.end.set(endYear, endMonth, endDay, endHour, endMinute);
	}
	
    Appointment(int beginYear, int beginMonth, int beginDay, int beginHour, int beginMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute, String seats, boolean premiere, boolean rehearsal) {
		this.begin.set(beginYear, beginMonth, beginDay, beginHour, beginMinute);
		this.end.set(endYear, endMonth, endDay, endHour, endMinute);
		this.seats = new String(seats);
		this.premiere = premiere;
		this.rehearsal = rehearsal;
	}
	
	public int[] getSeats() {
		int[] result = new int[seats.length()];
		for (int i = 0; i < result.length; i++) {
			result[i] = Integer.parseInt(seats.substring(i, i+1));
		}
		return result;
	}
	
	public void setSeats(String seats) {
		this.seats = new String(seats);
	}
	
	public boolean getPremiere() {
		return premiere;
	}
	
	public void setPremiere(boolean premiere) {
		this.premiere = premiere;
	}
	
	public boolean getRehearsal() {
		return rehearsal;
	}
	
	public void setRehearsal(boolean rehearsal) {
		this.rehearsal = rehearsal;
	}
	public void setBegin(int beginYear, int beginMonth, int beginDay, int beginHour, int beginMinute) {
		this.begin.set(beginYear, beginMonth, beginDay, beginHour, beginMinute);
	}
	
	public int[] getBegin() {
		int[] date = {this.begin.get(Calendar.YEAR), this.begin.get(Calendar.MONTH), this.begin.get(Calendar.DAY_OF_MONTH), this.begin.get(Calendar.HOUR_OF_DAY), this.begin.get(Calendar.MINUTE)};
		return date;
	}
	
	public void setEnd(int endYear, int endMonth, int endDay, int endHour, int endMinute) {
		this.end.set(endYear, endMonth, endDay, endHour, endMinute);
	}
	
	public int[] getEnd() {
		int[] date = {this.end.get(Calendar.YEAR), this.end.get(Calendar.MONTH), this.end.get(Calendar.DAY_OF_MONTH), this.end.get(Calendar.HOUR_OF_DAY), this.end.get(Calendar.MINUTE)};
		return date;
	}
	
	public String toUrl() {
		return "" + begin.getTime().getDate() + begin.getTime().getMonth() + (begin.getTime().getYear() + 1900) + begin.getTime().getHours() + begin.getTime().getMinutes();
	}
	
	public String toString() {
		String month, day, hour, minute;
		if (begin.get(Calendar.MONTH) < 9) month = "0" + String.valueOf(begin.get(Calendar.MONTH) + 1);
		else month = String.valueOf(begin.get(Calendar.MONTH) + 1);
		if (begin.get(Calendar.DAY_OF_MONTH) < 10) day = "0" + String.valueOf(begin.get(Calendar.DAY_OF_MONTH));
		else day = String.valueOf(begin.get(Calendar.DAY_OF_MONTH));
		if (begin.get(Calendar.HOUR_OF_DAY) < 10) hour = "0" + String.valueOf(begin.get(Calendar.HOUR_OF_DAY));
		else hour = String.valueOf(begin.get(Calendar.HOUR_OF_DAY));
		if (begin.get(Calendar.MINUTE) < 10) minute = "0" + String.valueOf(begin.get(Calendar.MINUTE));
		else minute = String.valueOf(begin.get(Calendar.MINUTE));
		return day + "." + month + "." + begin.get(Calendar.YEAR) + " " + hour + ":" + minute;
	}
}