package kassandraApp;

import org.salespointframework.core.DataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kassandraApp.model.AudioGuide;
import kassandraApp.model.EventCatalog;
import kassandraApp.model.GreatHall;
import kassandraApp.model.LittleHall;
import kassandraApp.model.Play;
import kassandraApp.model.Tour;

@Component
public class EventDataInitializer implements DataInitializer {

	private final EventCatalog eventCatalog;
	

	@Autowired
	public EventDataInitializer(EventCatalog eventCatalog){
		this.eventCatalog = eventCatalog;
	}

	@Override
	public void initialize() {
		this.eventCatalog.save(new AudioGuide("Audioguide", 10));
		this.eventCatalog.save(new Play("Schneewittchen", 10, GreatHall.getInstance()));
		this.eventCatalog.save(new Play("Winnie Pooh", 8, GreatHall.getInstance()));
		this.eventCatalog.save(new Play("Phantom der Oper", 12, LittleHall.getInstance()));
		this.eventCatalog.save(new Play("König der Löwen", 20, GreatHall.getInstance()));
		this.eventCatalog.save(new Tour("Führung", 12, "Marc Munzert"));

		this.eventCatalog.findByName("Führung").iterator().next().addAppointment(2015, 11, 22, 20, 15, 2015, 11, 22, 22, 15, false, false);
		
		
		this.eventCatalog.findByName("Schneewittchen").iterator().next().addAppointment(2015, 11, 22, 20, 15, 2015, 11, 22, 22, 15, false, false);
		this.eventCatalog.findByName("Winnie Pooh").iterator().next().addAppointment(2015, 2, 10, 20, 15, 2015, 2, 10, 22, 15, false, false);
		this.eventCatalog.findByName("Phantom der Oper").iterator().next().addAppointment(2015, 6, 1, 20, 15, 2015, 6, 1, 21, 45, false, false);
		this.eventCatalog.findByName("König der Löwen").iterator().next().addAppointment(2015, 10, 12, 20, 15, 2015, 11, 12, 22, 00, false, false);
		this.eventCatalog.findByName("König der Löwen").iterator().next().setExpired();
	}
}
