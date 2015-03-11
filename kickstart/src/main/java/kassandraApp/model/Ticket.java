package kassandraApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.money.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.quantity.Units;

@Entity
//@Table(name="TICKETS")
class Ticket extends Product {
//	@Id @GeneratedValue private long id;
	
	@Deprecated
	protected Ticket() {}
	
	public Ticket(String name, Money price) {
		super(name, price, Units.METRIC);
	}
	
//	@Column(name="ticket_id", nullable = false)
//	public long getTicketID() {
//		return id;
//	}
}