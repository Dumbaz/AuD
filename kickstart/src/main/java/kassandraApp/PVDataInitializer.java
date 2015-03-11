package kassandraApp;

import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kassandraApp.model.Customer;
import kassandraApp.model.CustomerRepository;
import kassandraApp.model.Ensemble;
import kassandraApp.model.EnsembleRepository;

@Component
public class PVDataInitializer implements DataInitializer  {

	
	private final UserAccountManager  userAccountManager;
	private final CustomerRepository customerRepository;
	private final EnsembleRepository ensembleRepository;
	
	@Autowired
	public PVDataInitializer(EnsembleRepository ensembleRepository, CustomerRepository customerRepository, UserAccountManager userAccountManager) {
		this.ensembleRepository = ensembleRepository;
		this.customerRepository = customerRepository;
		this.userAccountManager = userAccountManager;
	}
	
	@Override
	public void initialize() {
		
		//Prüft, ob Dummys schon da sind und nicht ein zweites Mal in die DB geladen werden
		if (customerRepository.findAll().iterator().hasNext()) {
			return;
		}
		
		//DummyAccount
		saveEmployee("Marc", "Munzert", "Actor");
		saveEmployee("Alisa", "Ottenberg", "Actor");
		saveEmployee("Valerie", "Müller", "Actor");
		saveEmployee("Luisa", "Schegner", "Technical Employee");
		saveEmployee("Max", "Mustercustomer", "Ticket Seller");

		
		//DummyEnsembles
		ensembleRepository.save(new Ensemble("Winnie Pooh" ));
		ensembleRepository.save(new Ensemble("Phantom der Oper"));
		ensembleRepository.save(new Ensemble("Schneewittchen" ));
		ensembleRepository.save(new Ensemble("König der Löwen" ));
	}
	
	private void saveEmployee(String firstname, String lastName, String role) {
		UserAccount userAccount = userAccountManager.create(firstname+" "+lastName, "123456789", new Role(role));
		userAccount.setFirstname(firstname);
		userAccount.setLastname(lastName);
		userAccountManager.save(userAccount);
		Customer customer = new Customer(userAccount, 2589);
		customerRepository.save(customer);
	}
}
