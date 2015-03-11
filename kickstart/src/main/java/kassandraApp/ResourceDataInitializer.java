/**
 * DataInitializer für die Ressourcen.
 * Initialisiert den Anfangsbestand an Ressourcen.
 * 
 * @author Henri Blankenstein
 * @version 1.00, 10.01.2015
 */

package kassandraApp;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.salespointframework.core.DataInitializer;
import org.salespointframework.inventory.Inventory;
import org.salespointframework.inventory.InventoryItem;
import org.salespointframework.quantity.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import kassandraApp.model.Requisite;
import kassandraApp.model.Resource;
import kassandraApp.model.ResourceCatalog;
import kassandraApp.model.Stage;
import kassandraApp.model.Store;
import kassandraApp.model.TechnicalObject;

@Component
public class ResourceDataInitializer implements DataInitializer {

	private final Inventory<InventoryItem> inventory;
	private final ResourceCatalog resourceCatalog;

	/**
	 * Konstruktor
	 * 
	 * @param inventory Inventar der Ressourcen mit ihrer Anzahl je Ressource
	 * @param resourceCatalog Katalog der Ressourcen.
	 */
	@Autowired
	public ResourceDataInitializer(Inventory<InventoryItem> inventory, ResourceCatalog resourceCatalog){
		Assert.notNull(inventory, "Inventory must not be null!");
		Assert.notNull(resourceCatalog, "ResourceCatalog must not be null!");

		this.inventory = inventory;
		this.resourceCatalog = resourceCatalog;
	}

	/**
	 * Die Methode initialisiert das Inventar.
	 */
	@Override
	public void initialize() {
		initializeCatalog(resourceCatalog, inventory);
	}

	/**
	 * Die Methode initialisiert den Katalog.
	 * 
	 * @param resCatalog ist der Ressourcenkatalog
	 * @param inventory ist das Inventar
	 */
	private void initializeCatalog(ResourceCatalog resCatalog, Inventory<InventoryItem> inventory) {

		if (resCatalog.findAll().iterator().hasNext()) {
			return;
		}

		resCatalog.save(new Stage(Store.getInstance(), "Große Bühne", 0));
		resCatalog.save(new Stage(Store.getInstance(), "Spezialbühne", 0));
		resCatalog.save(new TechnicalObject(Store.getInstance(), "Bass", 100));
		resCatalog.save(new TechnicalObject(Store.getInstance(), "Großer Scheinwerfer", 250));
		resCatalog.save(new TechnicalObject(Store.getInstance(), "Kleiner Scheinwerfer", 100));
		resCatalog.save(new TechnicalObject(Store.getInstance(), "Kleiner Scheinwerfer", 100));
		resCatalog.save(new Requisite(Store.getInstance(), "Schreibmaschine", 100));
		resCatalog.save(new Requisite(Store.getInstance(), "Statue", 44));
		resCatalog.save(new Requisite(Store.getInstance(), "Tisch", 0));
		resCatalog.save(new Requisite(Store.getInstance(), "Stuhl", 0));
		resCatalog.save(new Requisite(Store.getInstance(), "Stuhl", 0));
		resCatalog.save(new Requisite(Store.getInstance(), "Hocker", 0));
		resCatalog.save(new Requisite(Store.getInstance(), "Rüstung", 220));
		
		Calendar c = new GregorianCalendar();
		c.set(2015, 6, 10);
		Calendar d = new GregorianCalendar();
		d.set(2015, 5, 10);
		Calendar e = new GregorianCalendar();
		e.set(2015, 4, 10);
		Calendar f = new GregorianCalendar();
		f.set(2015, 2, 10);
		Resource r0 = resCatalog.findByName("Rüstung").iterator().next();
		r0.setExpired();
		r0.setDeletion(c.getTimeInMillis());
		resCatalog.save(r0);
		
		Resource r1 = resCatalog.findByName("Bass").iterator().next();
		r1.setCreation(c.getTimeInMillis());
		resCatalog.save(r1);
		Resource r2 = resCatalog.findByName("Großer Scheinwerfer").iterator().next();
		r2.setCreation(d.getTimeInMillis());
		resCatalog.save(r2);
		Resource r3 = resCatalog.findByName("Kleiner Scheinwerfer").iterator().next();
		r3.setCreation(e.getTimeInMillis());
		resCatalog.save(r3);
		Resource r4 = resCatalog.findByName("Schreibmaschine").iterator().next();
		r4.setCreation(f.getTimeInMillis());
		resCatalog.save(r4);
		Resource r5 = resCatalog.findByName("Statue").iterator().next();
		r5.setCreation(e.getTimeInMillis());
		resCatalog.save(r5);
		
		for (Resource res : resourceCatalog.findAll()) {
			InventoryItem inventoryItem = new InventoryItem(res, Units.ONE);
			inventory.save(inventoryItem);
		}
	}
}
