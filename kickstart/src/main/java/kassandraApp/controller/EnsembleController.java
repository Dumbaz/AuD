package kassandraApp.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kassandraApp.model.Customer;
import kassandraApp.model.CustomerRepository;
import kassandraApp.model.Ensemble;
import kassandraApp.model.EnsembleRepository;

@Controller
public class EnsembleController {

	final EnsembleRepository ensembleRepository;
	private final CustomerRepository customerRepository;
	
	@Autowired
	public EnsembleController(EnsembleRepository ensembleRepository, CustomerRepository customerRepository) {
		this.ensembleRepository = ensembleRepository;
		this.customerRepository = customerRepository;
	}
		
	@RequestMapping("/staff/ensemble/{ensemble}")
	public String editEsemble(Model model, @PathVariable("ensemble") String ensemble){
		
		model.addAttribute("ensembleCatalog", ensembleRepository.findAll());
		model.addAttribute("staffCatalog", customerRepository.findAll());
		
		ensemble = ensemble.replace("%20", " ");
		
		Optional<Ensemble> e =  ensembleRepository.findByName(ensemble);
		if(e.isPresent()) {
			model.addAttribute("ensembleActors",e.get().getActors());
		}
		Role actorRole = new Role("Actor");

		model.addAttribute("ensemble", ensemble);
		//erstellt eine Liste aller Customer mit der Rolle Schauspieler
		Set<Customer> allActors = new HashSet<Customer>();
			for(Customer c: customerRepository.findAll()){
				if(c.getUserAccount().hasRole(actorRole) && !e.get().getActors().contains(c)){
						allActors.add(c);
						
				}
			}
		model.addAttribute("allActors", allActors);
		
		return "staffEnsemble";
	}
	
	//Schauspieler zum Ensemble hinzufügen
	@RequestMapping("/staff/ensemble/addActor")
	public String addActor(Model model,
			  @RequestParam("uId") UserAccount userAccount,
	          @RequestParam("ensembleName") String ensembleName){
		
		Customer actor = customerRepository.findByUserAccount(userAccount);
		Optional<Ensemble> optionalEnsemble =  ensembleRepository.findByName(ensembleName);
		Ensemble ensemble = optionalEnsemble.get();
		ensemble.addMember(actor);
		ensembleRepository.save(ensemble);
	
		return "redirect:/staff/ensemble/" + ensembleName;
	}
	
	
	//Schauspieler aus dem Ensemble entfernen
	@RequestMapping("/staff/ensemble/removeActor")
	public String removeActor(Model model,
			@RequestParam("uId") UserAccount userAccount,
	        @RequestParam("ensembleName") String ensembleName){

		Customer actor = customerRepository.findByUserAccount(userAccount);
		
		for (Ensemble e : ensembleRepository.findAll()) {
			if (e.getName().equals(ensembleName)) {
				e.removeMember(actor);
				ensembleRepository.save(e);
			}
		}
	
		return "redirect:/staff/ensemble/" + ensembleName;
	}

}
