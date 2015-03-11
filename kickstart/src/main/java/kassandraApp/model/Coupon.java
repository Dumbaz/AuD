package kassandraApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.joda.money.Money;
import org.salespointframework.catalog.Product;
import org.salespointframework.quantity.Units;
import org.springframework.data.annotation.Id;

@Entity
//@Table(name="COUPONS")
public class Coupon extends Product {
//	@Id @GeneratedValue private long id;
	
	private String customer;
	
	@Deprecated
	public Coupon(){}
	
	public Coupon(String name, Money price, String customer) {
		super(name, Money.of(price), Units.METRIC);
		this.customer = customer;
	}
	
//	@Column(name="coupon_id", nullable = false)
//	public long getRoomID() {
//		return id;
//	}
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
}
