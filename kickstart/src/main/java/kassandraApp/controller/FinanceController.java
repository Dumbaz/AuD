package kassandraApp.controller;

import kassandraApp.model.Appointment;
import kassandraApp.model.AudioGuide;
import kassandraApp.model.Customer;
import kassandraApp.model.CustomerRepository;
import kassandraApp.model.Event;
import kassandraApp.model.EventCatalog;
import kassandraApp.model.GreatHall;
import kassandraApp.model.LittleHall;
import kassandraApp.model.Resource;
import kassandraApp.model.ResourceCatalog;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FinanceController {
	
	
	private final EventCatalog eventCatalog;
	private final ResourceCatalog resourceCatalog;
	private final CustomerRepository customerCatalog;
	
	private final Vector<Vector<Event>> eventArchive;
	private final Vector<Vector<Resource>> resourceArchive;
	private final Vector<Vector<Customer>> customerArchive;
	
	@Autowired
	public FinanceController(EventCatalog eventCatalog, ResourceCatalog resourceCatalog, CustomerRepository customerCatalog) {
		this.eventCatalog = eventCatalog;
		this.resourceCatalog = resourceCatalog;
		this.customerCatalog = customerCatalog;
		resourceArchive = new Vector<Vector<Resource>>();
		eventArchive = new Vector<Vector<Event>>();
		customerArchive = new Vector<Vector<Customer>>();
	}
		
	@RequestMapping("/finance/")
	public String finance(Model model) {
	
		//backup();
		
		float[] months = new float[12];
		
		for (Event ev : eventCatalog.findAll()) {
			//Theaterstücke
			if (!ev.getClass().getName().substring(19).equals("Tour") && !ev.getClass().getName().substring(19).equals("AudioGuide")) {
				for (Appointment a : ev.getAppointments()) {
					if(a.getBegin()[0] == 2015) { //year in url ..
						float revenue_of_a = 0;
						int i = 0;
						for (int s : a.getSeats()) {
							if(i<100) { if (s == 3 || s == 4) revenue_of_a += ev.getPrice().getAmountMinorInt() / 100.0; }
							else { if (s == 3 || s == 4) revenue_of_a += ev.getPrice().getAmountMinorInt() / 100.0 + 5.0; }
							if (s == 4) revenue_of_a += 2;
							if ((s == 3 || s == 4) && a.getPremiere()) revenue_of_a += 2; 
							i++;
						}
						months[a.getBegin()[1]] += revenue_of_a;
						//System.out.println(a.toString() + ": " + revenue_of_a);
					}
				}
			}
		}
		
		//Gastspiele
		for (Event ev : eventCatalog.findAll()) {
			if (ev.getClass().getName().substring(19).equals("GuestPlay")) {
				int price = 0; 
				if (ev.getRoom().equals("Kleiner Saal")) price = LittleHall.getInstance().getRent(); 
				if (ev.getRoom().equals("Großer Saal")) price = GreatHall.getInstance().getRent(); 
				for (Appointment a : ev.getAppointments()) {
					if (a.getBegin()[0] == 2015) {
						months[a.getBegin()[1]] += price;
					}
				}
			}
		}
		
		//Ressourcen
		for (Resource r : resourceCatalog.findAll()) {
			Calendar c = new GregorianCalendar();
			c.setTimeInMillis(r.getCreation());
			if (r.getExpired() == true) {
				Calendar d = new GregorianCalendar();
				d.setTimeInMillis(r.getDeletion());
				
				while (d.getTimeInMillis() >= c.getTimeInMillis()) {
					//System.out.println(c.get(Calendar.MONTH));
					if (c.get(Calendar.YEAR) == 2015) months[c.get(Calendar.MONTH)] -= r.getRent();
					c.add(Calendar.MONTH, 1);
				}
			}
			
			else {
				Calendar d = Calendar.getInstance();
				while (d.getTimeInMillis() >= c.getTimeInMillis()) {
					//System.out.println(c.get(Calendar.MONTH));
					if (c.get(Calendar.YEAR) == 2015) months[c.get(Calendar.MONTH)] -= r.getRent();
					c.add(Calendar.MONTH, 1);
				}	
			}
		}
		
		//Mitarbeiter
		for (Customer c : customerCatalog.findAll()) {
			Calendar e = new GregorianCalendar();
			e.setTimeInMillis(c.getCreation());
			if (c.getExpired() == true) {
				Calendar d = new GregorianCalendar();
				d.setTimeInMillis(c.getDeletion());
				
				while (d.getTimeInMillis() >= e.getTimeInMillis()) {
					//System.out.println(c.get(Calendar.MONTH));
					if (e.get(Calendar.YEAR) == 2015) months[e.get(Calendar.MONTH)] -= c.getPay();
					e.add(Calendar.MONTH, 1);
				}
			}
			
			else {
				Calendar d = Calendar.getInstance();
				while (d.getTimeInMillis() >= e.getTimeInMillis()) {
					//System.out.println(c.get(Calendar.MONTH));
					if (e.get(Calendar.YEAR) == 2015) months[e.get(Calendar.MONTH)] -= c.getPay();
					e.add(Calendar.MONTH, 1);
				}	
			}
		}
		
		model.addAttribute("jan", months[0]);
		model.addAttribute("feb", months[1]);
		model.addAttribute("mar", months[2]);
		model.addAttribute("apr", months[3]);
		model.addAttribute("may", months[4]);
		model.addAttribute("jun", months[5]);
		model.addAttribute("jul", months[6]);
		model.addAttribute("aug", months[7]);
		model.addAttribute("sep", months[8]);
		model.addAttribute("oct", months[9]);
		model.addAttribute("nov", months[10]);
		model.addAttribute("dec", months[11]);
		return "finance";
	}
	
	public void backup() {
		
		System.out.println("EV: " + eventCatalog.count() +"RES: " + resourceCatalog.count() + "; CUS: " + customerCatalog.count());
		
		Vector<Event> events = new Vector<Event>();
		for (Event e : eventCatalog.findAll()) events.add(e);
		eventArchive.add(events);
		
		Vector<Resource> resources = new Vector<Resource>();
		for (Resource r : resourceCatalog.findAll()) resources.add(r);
		resourceArchive.add(resources);
		
		Vector<Customer> customers = new Vector<Customer>();
		for (Customer c : customerCatalog.findAll()) customers.add(c);
		customerArchive.add(customers);

		System.out.println("EVV: " + eventArchive.size() + "RESV: " + resourceArchive.size() + "CUSV: " + customerArchive.size());
		
		for (Event e : eventCatalog.findAll()) {
			eventCatalog.delete(e); 
		}
		
		for (Event e : eventArchive.get(0)) {
			eventCatalog.save(e);
		}
		
		for (Customer c : customerCatalog.findAll()) {
			customerCatalog.delete(c);
		}
		
		for (Customer c : customerArchive.get(0)) {
			customerCatalog.save(c);
		}
		
	}
	
}
