package kassandraApp.controller;

import static org.joda.money.CurrencyUnit.EUR;

import java.util.Optional;
import java.util.Vector;

import kassandraApp.model.AmateurPlay;
import kassandraApp.model.Cabaret;
import kassandraApp.model.CustomerRepository;
import kassandraApp.model.Ensemble;
import kassandraApp.model.EnsembleRepository;
import kassandraApp.model.EventCatalog;
import kassandraApp.model.Event;
import kassandraApp.model.Appointment;
import kassandraApp.model.GreatHall;
import kassandraApp.model.GuestPlay;
import kassandraApp.model.LittleHall;
import kassandraApp.model.Play;
import kassandraApp.model.Tour;
import kassandraApp.model.AudioGuide;

import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventController {

	private final EventCatalog eventCatalog;
	private final CustomerRepository customerRepository;
	private final EnsembleRepository ensembleRepository;
	
	@Autowired
	public EventController(EventCatalog eventCatalog, CustomerRepository customerRepository, EnsembleRepository ensembleRepository) {
		this.eventCatalog = eventCatalog;
		this.customerRepository = customerRepository;
		this.ensembleRepository = ensembleRepository;
	}
	
	@RequestMapping("/")
	public String init(Model model) {
		
		model.addAttribute("catalog", eventCatalog.findAll());
		Event firstEvent;
		Appointment firstAppointment;
		for (Event ev : eventCatalog.findAll()) {
			if (ev.getAppointments().size() > 0 && !ev.getExpired() && ev.sellable()) {
				firstEvent = ev;
				firstAppointment = firstEvent.getAppointments().get(0);
				return "redirect:seats/" + firstEvent.getIdentifier() + "/" + firstAppointment.toUrl();
			}
 		}
		return null;
	}
	
	@RequestMapping("/seats/{event}/{appointment}")
	public String event(@PathVariable("event") Event event, @PathVariable("appointment") String appointment, Model model) {
		model.addAttribute("eventClass", event.getClass().getName().substring(19));
		model.addAttribute("event", event);
		model.addAttribute("appointment", appointment);
		model.addAttribute("catalog", eventCatalog.findAll());
		return "index";
	}
	
	@RequestMapping("/seats/{event}/{appointment}/{seats}")
	public String event(@PathVariable("event") Event event, @PathVariable("appointment") String appointment, @PathVariable("seats") String seats, Model model) {
		for (Appointment a : event.getAppointments()) {
			if (a.toUrl().equals(appointment)) a.setSeats(seats);
		}
		eventCatalog.save(event);
		return "redirect:../../../seats/" + event.getIdentifier() + "/" + appointment;
	}
	
	@RequestMapping("/events/")
	public String eventList(Model model){
		model.addAttribute("catalog", eventCatalog.findAll());
		return "eventList";
	}
	
	@RequestMapping("/events/{event}")
	public String eventEdit(@PathVariable("event") Event event, Model model){
		String eventList = "";
		for ( Event ev : eventCatalog.findAll()) {
			if (ev == event) {
				model.addAttribute("eventClass", ev.getClass().getName().substring(19));
			}
			else {
				eventList += ev.getName() + ",";
			}
		}
		model.addAttribute("eventList", eventList);
		model.addAttribute("staffCatalog", customerRepository.findAll());
		model.addAttribute("event", event);
		model.addAttribute("catalog", eventCatalog.findAll());
		
		return "eventEdit";
	}
	
	@RequestMapping("/events/{event}/appointments")
	public String appointmentList(@PathVariable("event") Event event, Model model){
		model.addAttribute("eventClass", event.getClass().getName().substring(19));
		model.addAttribute("event", event);
		model.addAttribute("catalog", eventCatalog.findAll());
		return "eventAppointmentList";
	}
	
	@RequestMapping("/events/{event}/appointments/{appointment}")
	public String appointmentEdit(@PathVariable("event") Event event, @PathVariable("appointment") String appointment, Model model){
		model.addAttribute("eventClass", event.getClass().getName().substring(19));
		
		String beginYearList ="";
		String beginMonthList = "";
		String beginDateList = "";
		String beginHourList = "";
		String beginMinuteList = "";
		String endYearList =  "";
		String endMonthList = "";
		String endDateList = "";
		String endHourList = "";
		String endMinuteList = "";
		
		for (Appointment a : event.getAppointments()) {
			if (a.toUrl().equals(appointment)) {
				int[] begin = a.getBegin();
				model.addAttribute("beginYear", begin[0]);
				if (begin[1] < 9) model.addAttribute("beginMonth", "0" + (begin[1] + 1));
				else model.addAttribute("beginMonth", (begin[1] + 1));
				if (begin[2] < 10) model.addAttribute("beginDay", "0" + begin[2]);
				else model.addAttribute("beginDay", begin[2]);
				if (begin[3] < 10) model.addAttribute("beginHour", "0" + begin[3]);
				else model.addAttribute("beginHour", begin[3]);
				if (begin[4] < 10) model.addAttribute("beginMinute", "0" + begin[4]);
				else model.addAttribute("beginMinute", begin[4]);
				
				int[] end = a.getEnd();
				model.addAttribute("endYear", end[0]);
				if (end[1] < 9) model.addAttribute("endMonth", "0" + (end[1] + 1));
				else model.addAttribute("endMonth", (end[1] + 1));
				if (end[2] < 10) model.addAttribute("endDay", "0" + end[2]);
				else model.addAttribute("endDay", end[2]);
				if (end[3] < 10) model.addAttribute("endHour", "0" + end[3]);
				else model.addAttribute("endHour", end[3]);
				if (end[4] < 10) model.addAttribute("endMinute", "0" + end[4]);
				else model.addAttribute("endMinute", end[4]);
			}
			else {
				int[] begin = a.getBegin();
				beginYearList += begin[0] + ",";
				beginMonthList += begin[1] + ",";
				beginDateList += begin[2] + ",";
				beginHourList += begin[3] + ",";
				beginMinuteList += begin[4] + ",";
				
				int[] end = a.getEnd();
				endYearList += end[0] + ",";
				endMonthList += end[1] + ",";
				endDateList += end[2] + ",";
				endHourList += end[3] + ",";
				endMinuteList += end[4] + ",";
			}
		}
		
		for (Event ev : eventCatalog.findAll()) {
			if (ev != event && ev.getExpired() == false && !event.getClass().getName().substring(19).equals("AudioGuide") && !event.getClass().getName().substring(19).equals("Tour") && !ev.getClass().getName().substring(19).equals("AudioGuide") && !ev.getClass().getName().substring(19).equals("Tour")) {
				if (ev.getRoom().equals(event.getRoom())) {
					System.out.println(ev.getName());
					for (Appointment a : ev.getAppointments()) {
						int[] begin = a.getBegin();
						beginYearList += begin[0] + ",";
						beginMonthList += begin[1] + ",";
						beginDateList += begin[2] + ",";
						beginHourList += begin[3] + ",";
						beginMinuteList += begin[4] + ",";
							
						int[] end = a.getEnd();
						endYearList += end[0] + ",";
						endMonthList += end[1] + ",";
						endDateList += end[2] + ",";
						endHourList += end[3] + ",";
						endMinuteList += end[4] + ",";
					}
				}	
			}
		}	
		
		model.addAttribute("beginYearList", beginYearList);
		model.addAttribute("beginMonthList", beginMonthList);
		model.addAttribute("beginDateList", beginDateList);
		model.addAttribute("beginHourList", beginHourList);
		model.addAttribute("beginMinuteList", beginMinuteList);
		
		model.addAttribute("endYearList", endYearList);
		model.addAttribute("endMonthList", endMonthList);
		model.addAttribute("endDateList", endDateList);
		model.addAttribute("endHourList", endHourList);
		model.addAttribute("endMinuteList", endMinuteList);
		
		model.addAttribute("event", event);
		model.addAttribute("appointment", appointment);
		model.addAttribute("catalog", eventCatalog.findAll());
		return "eventAppointmentEdit";
	}
	
	@RequestMapping("/events/{event}/appointments/{appointment}/edit")
	public String appointmentEdit(@RequestParam("begin") String begin, @RequestParam("end") String end, @RequestParam(value = "premiere", required = false, defaultValue = "false") Boolean premiere, @RequestParam(value = "rehearsal", required = false, defaultValue = "false") Boolean rehearsal,  @PathVariable("event") Event event, @PathVariable("appointment") String appointment) {
		
		int beginYear = Integer.parseInt(begin.substring(0, 4));
		int beginMonth = Integer.parseInt(begin.substring(5, 7)) - 1;
		int beginDay = Integer.parseInt(begin.substring(8, 10));
		int beginHour = Integer.parseInt(begin.substring(11, 13));
		int beginMinute = Integer.parseInt(begin.substring(14, 16));

		int endYear = Integer.parseInt(end.substring(0, 4));
		int endMonth = Integer.parseInt(end.substring(5, 7)) - 1;
		int endDay = Integer.parseInt(end.substring(8, 10));
		int endHour = Integer.parseInt(end.substring(11, 13));
		int endMinute = Integer.parseInt(end.substring(14, 16));
		
		Vector<Appointment> appointments = new Vector<Appointment>();
			for (Appointment a : event.getAppointments()) {
				if (a.toUrl().equals(appointment)) {
					a.setBegin(beginYear, beginMonth, beginDay, beginHour, beginMinute);
					a.setEnd(endYear, endMonth, endDay, endHour, endMinute);
					a.setPremiere(premiere);
					a.setRehearsal(rehearsal);
					appointments.add(a);
				}
				else appointments.add(a);
			}
		event.setAppointments(appointments);
		eventCatalog.save(event);
		
		return "redirect:/events/" + event.getIdentifier() + "/appointments";
	}
	
	@RequestMapping("/events/{event}/appointments/{appointment}/delete")
	public String appointmentDelete(@PathVariable("event") Event event, @PathVariable("appointment") String appointment, Model model){
		Vector<Appointment> appointments = new Vector<Appointment>();
		for (Appointment a : event.getAppointments()) {
			if (!a.toUrl().equals(appointment)) appointments.add(a);
		}
		event.setAppointments(appointments);
		eventCatalog.save(event);
		
		return "redirect:/events/" + event.getIdentifier() + "/appointments";
	}
	
	@RequestMapping("/events/{event}/appointments/add")
	public String appointmentAdd(@PathVariable("event") Event event, Model model){
		model.addAttribute("event", event);
		model.addAttribute("eventClass", event.getClass().getName().substring(19));

		String beginYearList ="";
		String beginMonthList = "";
		String beginDateList = "";
		String beginHourList = "";
		String beginMinuteList = "";
		String endYearList =  "";
		String endMonthList = "";
		String endDateList = "";
		String endHourList = "";
		String endMinuteList = "";
		
		for (Event ev : eventCatalog.findAll()) {
			if (ev.getExpired() == false && !event.getClass().getName().substring(19).equals("AudioGuide") && !event.getClass().getName().substring(19).equals("Tour") && !ev.getClass().getName().substring(19).equals("AudioGuide") && !ev.getClass().getName().substring(19).equals("Tour")) {
				if (ev.getRoom().equals(event.getRoom())) {
					System.out.println(ev.getName());
					for (Appointment a : ev.getAppointments()) {
						int[] begin = a.getBegin();
						beginYearList += begin[0] + ",";
						beginMonthList += begin[1] + ",";
						beginDateList += begin[2] + ",";
						beginHourList += begin[3] + ",";
						beginMinuteList += begin[4] + ",";
							
						int[] end = a.getEnd();
						endYearList += end[0] + ",";
						endMonthList += end[1] + ",";
						endDateList += end[2] + ",";
						endHourList += end[3] + ",";
						endMinuteList += end[4] + ",";
					}
				}	
			}
		}	
		
		model.addAttribute("beginYearList", beginYearList);
		model.addAttribute("beginMonthList", beginMonthList);
		model.addAttribute("beginDateList", beginDateList);
		model.addAttribute("beginHourList", beginHourList);
		model.addAttribute("beginMinuteList", beginMinuteList);
		
		model.addAttribute("endYearList", endYearList);
		model.addAttribute("endMonthList", endMonthList);
		model.addAttribute("endDateList", endDateList);
		model.addAttribute("endHourList", endHourList);
		model.addAttribute("endMinuteList", endMinuteList);
		
		model.addAttribute("catalog", eventCatalog.findAll());
		return "eventAppointmentAdd";
	}
	
	@RequestMapping(value = "/events/{event}/appointments/add/done", method = RequestMethod.POST)
	public String appointmentAdd(@PathVariable("event") Event event, @RequestParam("begin") String begin, @RequestParam("end") String end, @RequestParam(value="premiere",  required = false, defaultValue = "false") Boolean premiere, @RequestParam(value="rehearsal",  required = false, defaultValue = "false") Boolean rehearsal) {
		
		int beginYear = Integer.parseInt(begin.substring(0, 4));
		int beginMonth = Integer.parseInt(begin.substring(5, 7)) - 1;
		int beginDay = Integer.parseInt(begin.substring(8, 10));
		int beginHour = Integer.parseInt(begin.substring(11, 13));
		int beginMinute = Integer.parseInt(begin.substring(14, 16));
		
		int endYear = Integer.parseInt(end.substring(0, 4));
		int endMonth = Integer.parseInt(end.substring(5, 7)) - 1;
		int endDay = Integer.parseInt(end.substring(8, 10));
		int endHour = Integer.parseInt(end.substring(11, 13));
		int endMinute = Integer.parseInt(end.substring(14, 16));
		
		event.addAppointment(beginYear, beginMonth, beginDay, beginHour, beginMinute, endYear, endMonth, endDay, endHour, endMinute, premiere, rehearsal);
		eventCatalog.save(event);
	
		return "redirect:/events/" + event.getIdentifier() + "/appointments";
	}
	
	@RequestMapping(value = "/events/{event}/edit", method = RequestMethod.POST)
	public String edit(@PathVariable("event") Event event, @RequestParam("name") String name, @RequestParam(value="room", required = false, defaultValue = "") String room, @RequestParam("price") int price, @RequestParam(value="employee", required = false, defaultValue = "") String employee) {

		event.setName(name); //Namen ändern
		if (room.equals("Großer Saal")) {event.setRoom(GreatHall.getInstance());} //Raum ändern
		else if (room.equals("Kleiner Saal")) {event.setRoom(LittleHall.getInstance());}
		event.setPrice(Money.of(EUR, price)); //Preis ändern
		if (employee != "") {event.setEmployee(employee); }
		eventCatalog.save(event);
		
		return "redirect:/events/";
	}
	
	@RequestMapping("/events/add/{type}")
	public String add(@PathVariable("type") String type, Model model){
		model.addAttribute("staffCatalog", customerRepository.findAll());
		model.addAttribute("catalog", eventCatalog.findAll());
		model.addAttribute("eventClass", type);
		
		String eventList = "";
		for (Event ev : eventCatalog.findAll()) {
			eventList += ev.getName() + ",";
		}
		model.addAttribute("eventList", eventList);
		
		return "eventAdd";
	}
	
	@RequestMapping(value = "/events/add/{type}/done", method = RequestMethod.POST)
	public String add(@PathVariable("type") String type, @RequestParam("name") String name, @RequestParam(value = "room", required = false) String room, @RequestParam("price") int price, @RequestParam(value="fee",  required = false) Integer fee, @RequestParam(value = "employee", required = false) String employee, Model model){
		
		if (type.equals("play")) {
			if(room.equals("Großer Saal")) eventCatalog.save(new Play(name, price, GreatHall.getInstance()));
			else eventCatalog.save(new Play(name, price, LittleHall.getInstance()));
			ensembleRepository.save(new Ensemble(name));
		}
		if (type.equals("cabaret")) {
			if(room.equals("Großer Saal")) eventCatalog.save(new Cabaret(name, price, GreatHall.getInstance()));
			else eventCatalog.save(new Cabaret(name, price, LittleHall.getInstance()));
		}
		if (type.equals("amateurplay")) {
			if(room.equals("Großer Saal")) eventCatalog.save(new AmateurPlay(name, price, GreatHall.getInstance()));
			else eventCatalog.save(new AmateurPlay(name, price, LittleHall.getInstance()));
		}
		if (type.equals("guestplay")) {
			if(room.equals("Großer Saal")) eventCatalog.save(new GuestPlay(name, price, GreatHall.getInstance()));
			else eventCatalog.save(new GuestPlay(name, price, LittleHall.getInstance()));
		}
		if (type.equals("tour")) {
			eventCatalog.save(new Tour(name, price, employee));
		}
		return "redirect:/events/";
	}	
	
	@RequestMapping("/events/delete/{event}")
	public String delete(@PathVariable("event") Event event) {

		event.setExpired();
		eventCatalog.save(event);
		Optional<Ensemble> ensemble =  ensembleRepository.findByName(event.getName());
		if(ensemble.isPresent()) {
			ensembleRepository.delete(ensemble.get());
		}
		return "redirect:/events/";
	}	

	//Tour
	
	@RequestMapping("/seats/{event}/{appointment}/sell")
	public String sellTour(@PathVariable("event") Event event, @PathVariable("appointment") String choosenAppointment, Model model) {
		
		((Tour) event).setSold(((Tour) event).getSold() + 1);
		eventCatalog.save(event);
			
		return "redirect:/seats/" + event.getIdentifier() + "/" + choosenAppointment;
	}

	//Audioguide 
	
	@RequestMapping("/seats/{event}/sell")
	public String sellAudioGuide(@PathVariable("event") Event event, Model model) {
		
		((AudioGuide) event).setSold(((AudioGuide) event).getSold() + 1);
		((AudioGuide) event).setLentCount(((AudioGuide) event).getLentCount() + 1);
		eventCatalog.save(event);
			
		return "redirect:/seats/" + event.getIdentifier() + "/0";
	}
	
	@RequestMapping("/seats/{event}/back")
	public String backAudioGuide(@PathVariable("event") Event event, Model model) {

		if (((AudioGuide) event).getLentCount() > 0) {
			((AudioGuide) event).setLentCount(((AudioGuide) event).getLentCount() - 1);
			eventCatalog.save(event);
		}
		
		return "redirect:/seats/" + event.getIdentifier() + "/0";
	}
	
	@RequestMapping("/seats/audioguide/coupon")
	public String couponAudioGuide(Model model) {
		for ( Event ev : eventCatalog.findAll()) {
			if (ev.toUrl().equals("audioguide")) {
				System.out.println("Audioguide coupon");
			}
		}
		return "redirect:/seats/audioguide/0";
	}
}

