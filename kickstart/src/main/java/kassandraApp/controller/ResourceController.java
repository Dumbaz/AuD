/**
 * Controller für die Ressourcen.
 * 
 * @author Henri Blankenstein
 * @version 1.00, 10.01.2015
 */

package kassandraApp.controller;


import kassandraApp.model.GreatHall;
import kassandraApp.model.LittleHall;
import kassandraApp.model.Requisite;
import kassandraApp.model.Resource;
import kassandraApp.model.ResourceCatalog;
import kassandraApp.model.Stage;
import kassandraApp.model.Store;
import kassandraApp.model.TechnicalObject;

import org.salespointframework.inventory.Inventory;
import org.salespointframework.inventory.InventoryItem;
import org.salespointframework.quantity.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResourceController {
	private final Inventory<InventoryItem> inventory;
	private final ResourceCatalog resourceCatalog;
	
	/**
	 * Die Methode ist der Konstruktor.
	 * 
	 * @param resCatalog ist der Katalog mit den Ressourcen
	 * @param inventory ist das Inventar mit den Ressourcen und den Stückzahlen. Da es alle Ressourcen nur einmal gibt, wird im Code die Anzahl auf 1 gesetzt.
	 */
	@Autowired
	public ResourceController(ResourceCatalog resCatalog, Inventory<InventoryItem> inventory) {
		this.inventory = inventory;
		this.resourceCatalog = resCatalog;
	}
	
	/**
	 * Die Methode stellt eine Liste der Ressourcen zur Verfügung. Dies wird benötigt, damit der View die Liste darstellen kann.
	 * 
	 * @param model
	 * @return resourceList.html soll aufgerufen werden
	 */
	@RequestMapping("/resources/") //Ressourcen verwaltung
	public String resourceList(Model model) {
		model.addAttribute("catalog", resourceCatalog.findAll());
		return "resourceList";
	}
	
	/**
	 * Die Methode ruft die Eingabemaske auf.
	 * 
	 * @param type ist der Typ der Ressorce
	 * @param model
	 * @return weiterleiten auf ressourceAdd.html
	 */
	@RequestMapping("/resources/add/{type}") //Ressourcen einstellen
	public String resourceAdd(@PathVariable("type") String type, Model model) {
		model.addAttribute("type", type);
		model.addAttribute("catalog", resourceCatalog.findAll());
		return "resourceAdd";
	}
	
	/**
	 * Die Methode wird aufgerufen, wenn ein neuer Eintrag erstellt wurde und auf senden im View geklickt wird.
	 * 
	 * @param type der Typ der Ressource
	 * @param name der Name der Ressource
	 * @param rent die Miete der Ressource, falls diese extern ist
	 * @return weiterleiten wieder auf die Ressourcenliste
	 */
	@RequestMapping(value = "/newEntry", method=RequestMethod.POST)
	public String newEntry(@RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("rent") int rent) {
		Resource res = null;
		//Typen bestimmen
		if(type.equals("Buehne")) {
			res = resourceCatalog.save(new Stage(Store.getInstance(), name, 0));
		}
		if(type.equals("TechnischeAnlage")) {
			res = resourceCatalog.save(new TechnicalObject(Store.getInstance(), name, rent));
		}
		if(type.equals("Requisite")) {
			res = resourceCatalog.save(new Requisite(Store.getInstance(), name, rent));
		}	
		//Speichern
		InventoryItem inventoryItem = new InventoryItem(res, Units.ONE);
		inventory.save(inventoryItem);

		return "redirect:resources/";
	}
	
	/**
	 * Die Methode ruft die Bearbeitungsmaske auf.
	 * 
	 * @param res ist die Ressource die bearbeitet werden soll
	 * @param model
	 * @return Weiterleitung auf die resourceEdit.html
	 */
	@RequestMapping("/resources/{pid}")
	public String resourceEdit(@PathVariable("pid") Resource res, Model model) {
		model.addAttribute("res", res);
		model.addAttribute("catalog", resourceCatalog.findAll());
		return "resourceEdit";
	}
	
	/**
	 * Die Methode wird aufgerufen, wenn eine Ressource überarbeitet wurde und auf senden im View geklickt wird.
	 * 
	 * @param res ist die Ressource
	 * @param name ist der Name der Ressource
	 * @param room ist der Raum der Ressource
	 * @param rent ist die Miete der Ressource
	 * @return Weiterleitung auf die Ressourcenliste
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@RequestParam("pid") Resource res, @RequestParam("name") String name, @RequestParam("room") String room, @RequestParam("rent") int rent) {
		//Namen ändern
		res.setName(name);
		//Raum ändern
		if(room.equals("Lager")) res.setRoom(Store.getInstance());
		if(room.equals("Großer Saal")) res.setRoom(GreatHall.getInstance());
		if(room.equals("Kleiner Saal")) res.setRoom(LittleHall.getInstance());
		//Miete ändern
		if (rent != res.getRent()) {
			res.setExpired();
			Resource resnew = new Resource(res.getName(), res.getRoom(), res.getType(), rent);
			resourceCatalog.save(resnew);
		}
		
		//Speichern
		resourceCatalog.save(res);
		return "redirect:/resources/";
	}
	
	/**
	 * Die Methode wird aufgerufen, wenn eine Ressource gelöscht werden soll.
	 * 
	 * @param res ist die Ressource
	 * @param model
	 * @return Weoterleitung auf die Ressourcenliste
	 */
	@RequestMapping("resources/{pid}/delete")
	public String loeschen(@PathVariable("pid") Resource res, Model model) {
		inventory.delete(inventory.findByProduct(res).get());
		
		res.setExpired();
		resourceCatalog.save(res);
		
		return "redirect:/resources/";
	}
}
