package kassandraApp.controller;

import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;








import kassandraApp.model.CustomerRepository;
import kassandraApp.model.Customer;
import kassandraApp.model.Ensemble;
import kassandraApp.model.EnsembleRepository;

@Controller
public class PersonnelController {

	private final UserAccountManager  userAccountManager;
	private final CustomerRepository customerRepository;
	private final EnsembleRepository ensembleRepository;
	
	private Role roleE = new Role("Actor");
	private Role roleZ = new Role("Technical Employee");
	private Role roleD = new Role("Ticket Seller");
	
	@Autowired  //hier werden Beans initialisiert
	public PersonnelController(UserAccountManager userAccountManager, CustomerRepository customerRepository, EnsembleRepository ensembleRepository) {
		this.userAccountManager = userAccountManager;
		this.customerRepository = customerRepository;
		this.ensembleRepository = ensembleRepository;
	}
	
	//Verlinkung auf das Registrierungsformular
	@RequestMapping("/staff/add") //Kopfzeile
	public String registerEmployee(Model model) {
		model.addAttribute("ensembleCatalog", ensembleRepository.findAll());
		model.addAttribute("staffCatalog", customerRepository.findAll());
		return "staffAdd"; //Template welches aufgerufen wird
	}
	
	//Registrierung eines Mitarbeiters	
	@RequestMapping("staff/createUser")
	public String createUser(@RequestParam("firstname") String firstname,
			 @RequestParam("lastname") String lastname,
			 @RequestParam("role") String role, 
			 @RequestParam("password") String password,
			 @RequestParam("pay") Integer pay)
	{	
		
		//Test auf leeres Eingabefeld
		if(firstname.equals("") || lastname.equals("") || role.equals("") || password.equals("") || pay.equals("") || pay < 0){
			return "registerEmployee";	
		}
		//kein Eingabefeld ist leer
		boolean duplicateCheck=true;
		UserAccount userAccount = userAccountManager.create(firstname+" "+lastname, password, new Role(role));
		userAccount.setFirstname(firstname);
		userAccount.setLastname(lastname);
		for(Customer c: customerRepository.findAll()){
			//Prüfunen ob ein Useraccount bereits mit diesen Daten angelegt wurde
			if(c.getUserAccount().equals(userAccount)) {
				duplicateCheck=false; // bereits vorhanden
			}
		}
		//Useraccount ist noch nicht vorhanden
		if(duplicateCheck){
			userAccountManager.save(userAccount);
			Customer customer = new Customer(userAccount, pay);
			customerRepository.save(customer);
			return "redirect:employee";
		}
		//Rückverlinkung
		
		return "registerEmployee";
	}
		
	//Verlinkung auf die Bearbeitungsseite		
	@RequestMapping("/staff/{user}")
	public String editUser(Model model, @PathVariable("user") String user)
	{
		model.addAttribute("ensembleCatalog", ensembleRepository.findAll());
		model.addAttribute("staffCatalog", customerRepository.findAll());
		
		user = user.replace("%20", " ");
		for (Customer c : customerRepository.findAll()) {
			if (c.getUserAccount().getId().toString().equals(user) && c.getExpired() == false) {
				for(Role r : c.getUserAccount().getRoles()) {
					model.addAttribute("customerRole", r.getName());
				}
				model.addAttribute("customer", c);
				return "staffEdit";
			}
		}
		return "redirect:/staff/";
	}
	
	//Bearbeitung speichern	
	@RequestMapping("/staff/saveUser")
	public String saveUser(@RequestParam("oldname") String oldname, @RequestParam("name") String name, @RequestParam("role") String role, @RequestParam("password") String password, @RequestParam("pay") Integer pay)
	{
		
		for (Customer c : customerRepository.findAll()) {
			
			if (c.getUserAccount().getId().toString().equals(oldname) && c.getExpired() == false) {
				c.setExpired();
				customerRepository.save(c);
				
				UserAccount userAccount = c.getUserAccount();
				userAccountManager.disable(c.getUserAccount().getId());
				String names[] = name.split(" ");
				userAccount.setFirstname(names[0]);
				userAccount.setLastname(names[1]);
				
				if(userAccount.hasRole(roleE))
				{
					userAccount.remove(roleE);
				}
				if(userAccount.hasRole(roleZ))
				{
					userAccount.remove(roleZ);
				}
				if(userAccount.hasRole(roleD))
				{
					userAccount.remove(roleD);
				}
				userAccount.add(new Role(role));
				userAccountManager.changePassword(userAccount, password);
				userAccountManager.save(userAccount);
				Customer customer = new Customer(userAccount, pay);
				customerRepository.save(customer);
			}
		}
		
		return "redirect:/staff/";			
	}
	
	//Löschen eines Mitarbeiters
	@RequestMapping("/staff/delete/{user}")
	public String deleteUser(@PathVariable("user") UserAccount userAccount)
	{
		for (Customer c : customerRepository.findAll()) {
			if (c.getUserAccount().equals(userAccount)) {
				userAccountManager.disable(userAccount.getIdentifier());
				c.setExpired();
				customerRepository.save(c);
			}
		}
			
		return "redirect:/staff/";
	}
	
	@RequestMapping("/staff/")
	public String staff(Model model){
		model.addAttribute("ensembleCatalog", ensembleRepository.findAll());
		model.addAttribute("staffCatalog", customerRepository.findAll());
		return "staffList";
	}
}	

	
