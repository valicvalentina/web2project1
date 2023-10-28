package hr.fer.web2.teamsbackend.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import hr.fer.web2.teamsbackend.RasporedGenerator;
import hr.fer.web2.teamsbackend.domain.Competition;
import hr.fer.web2.teamsbackend.domain.Participant;
import hr.fer.web2.teamsbackend.domain.Runda;
import hr.fer.web2.teamsbackend.service.CompetitionService;
import hr.fer.web2.teamsbackend.service.ParticipantService;
import hr.fer.web2.teamsbackend.service.RoundService;

	@Controller
	public class HomeController {

		@Autowired
	    private CompetitionService competitionService;
		
		@Autowired
		private ParticipantService participantService;
		
		@Autowired
		private RoundService roundService;
		
		@Autowired
		private RasporedGenerator rasporedGenerator;


	    @GetMapping("/")
	    public String home(Model model, Authentication authentication) {
	    	List<Competition> natjecanja = new ArrayList<>();
	    	
	    	if(authentication != null) {
	    		if (authentication instanceof OAuth2AuthenticationToken) {
	                OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
	                String name = (String) oauthToken.getPrincipal().getAttributes().get("name");
	                if (name != null) {
	                    model.addAttribute("username", name);
	                }
	    		}else {
	                model.addAttribute("username", "Korisnik");
	            }
	    	 String createdBy = authentication.getName();
	    	 natjecanja = competitionService.findByCreatedBy(createdBy);
	    	} else {
	    		 return "redirect:/login";
	    	}
	         model.addAttribute("natjecanja", natjecanja);
	         model.addAttribute("authentication", authentication);
	        return "index";
	    }

	    @GetMapping("/profile")
	    public String profile() {
	        return "profile";
	    }
	    
	    @GetMapping("/competition/{competitionId}")
	    public String prikaziNatjecanje(@PathVariable Long competitionId, Model model) {
	        Competition natjecanje = competitionService.getCompetitionById(competitionId);
	        if (natjecanje == null) {
	            // Obrada greške ako natjecanje nije pronađeno
	            return "error";
	        }
	        
	        // dohvacanje natjecatelja za odabrano natjecanje iz baze podataka
	        List<Participant> natjecatelji = participantService.findByCompetitionId(competitionId);
	        
	        List<Runda> rasporedKola = roundService.getAllRoundsByCompetitionId(competitionId);

	        // Ako nema pronađenih rundi, generiram raspored
	        if (rasporedKola.isEmpty()) {
	            rasporedKola = rasporedGenerator.generirajRasporedKola(natjecatelji);

	            // Spremanje generirane runde u bazu
	            for (Runda runda : rasporedKola) {
	            	runda.setCompetition(natjecanje);
	            	runda.setRezultat("0:0");
	                roundService.spremiRundu(runda);
	                runda.setBodoviPrvog(0);
	                runda.setBodoviDrugog(0);
	            }
	        }
	        Collections.sort(rasporedKola, Comparator.comparingInt(Runda::getBrojKola));
	        for (Runda runda : rasporedKola) {
	        	String rezultat = runda.getRezultat();
	            runda.setRezultat(rezultat);

	        }
	        model.addAttribute("natjecanje", natjecanje);
	       model.addAttribute("rasporedKola", rasporedKola);

	        return "raspored";
	    }
	    
	    @GetMapping("/competition/results/{competitionId}")
	    public String prikaziPoredak(@PathVariable Long competitionId, Model model) {
	    	 Competition natjecanje = competitionService.getCompetitionById(competitionId);
		        if (natjecanje == null) {
		
		            return "error";
		        }
		        
		        List<Runda> rasporedKola = roundService.getAllRoundsByCompetitionId(competitionId);
		        List<Participant> natjecatelji = participantService.findByCompetitionId(competitionId);
		        Collections.sort(natjecatelji, Comparator.comparingDouble(Participant::getBodovi).reversed());
		        model.addAttribute("natjecanje", natjecanje);
		        model.addAttribute("natjecatelji", natjecatelji);
		        model.addAttribute("rasporedKola", rasporedKola);
	    	
	    	return "poredak";
	    }
	}

