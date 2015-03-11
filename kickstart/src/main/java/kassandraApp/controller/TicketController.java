package kassandraApp.controller;

import java.util.Date;

import kassandraApp.model.CouponCatalog;
import kassandraApp.model.Coupon;
import kassandraApp.model.TicketCatalog;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {
	private final CouponCatalog couponCatalog;
	private final TicketCatalog ticketCatalog;
	
	@Autowired
	public TicketController(CouponCatalog couponCatalog, TicketCatalog ticketCatalog) {
		this.couponCatalog = couponCatalog;
		this.ticketCatalog = ticketCatalog;
	}
	
	@RequestMapping("/TicketsalesCoupons") //Verkauf von Gutscheinen Main
	public String ticketsalescoupons() {
		return "ticketsalescoupons";	
	}
	
	@RequestMapping(value="/neuCoupon")
	public String neucoupon() {
		return "neuCoupon";
	}
	
	// Coupon kaufen
	@RequestMapping(value="/neuCoupon", method=RequestMethod.POST) //Verkauf von Gutscheinen Main
	public String newCoupon(Model model, @RequestParam("customer") String customer /*, @RequestParam("number") int number*/) {
		
		if(customer != null) {
//			for(int i=0; i<number; i++) {
				Money money = Money.of(CurrencyUnit.EUR, 20);
				Date date = new Date();
				String name = customer+date;
				couponCatalog.save(new Coupon(name, money ,customer));
				System.out.println("Coupon: "+name+" verkauft.");
//			}
		} else {
			System.out.println("Feld 'Käufer' darf nicht leer sein.");
		}
		return "neuCoupon";	
	}
	
	// Premiere +2€
	// Sitzreihe Loge, Parkett +5€
	// Abendkasse +2
}
