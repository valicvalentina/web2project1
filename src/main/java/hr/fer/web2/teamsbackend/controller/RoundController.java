package hr.fer.web2.teamsbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RestController
@RequestMapping("/round")


public class RoundController {
	@Autowired
	private RoundService roundService;
	@Autowired
	private ParticipantService participantService;

	@Autowired
	private CompetitionService competitionService;
	Competition prva;
     Competition compIzBaze;
	 @PostMapping("")
	    public RedirectView spremiUređeniRezultat(@ModelAttribute Runda runda, @RequestParam Long competitionId) {
	      
	       Runda rundaIzBaze = roundService.getRoundById(runda.getId());
	     Participant participantIzBaze1 = participantService.getParticipantById(runda.getNatjecatelj1().getId());
	      Participant participantIzBaze2 = participantService.getParticipantById(runda.getNatjecatelj2().getId());
	   
	      Competition compIzBaze = competitionService.getCompetitionById(competitionId);
	      String bodovanje = compIzBaze.getScoringSystem();
	      String[] bodovi = bodovanje.split("/");
	      String pobjeda = bodovi[0];
	      String  remi = bodovi[1];  
	      String  poraz = bodovi[2]; 
	      
	      System.out.println(bodovanje);
	        System.out.println(participantIzBaze1.getName());
	        System.out.println(participantIzBaze2.getName());
	        // Ažuriranje rezultata kola
	        rundaIzBaze.setRezultat(runda.getRezultat());
	        String[] resultParts = runda.getRezultat().split(":");
	        if (resultParts.length == 2) {
	            int score1 = Integer.parseInt(resultParts[0]);
	            int score2 = Integer.parseInt(resultParts[1]);
	            if(rundaIzBaze.getBodoviPrvog()!=0 ||rundaIzBaze.getBodoviDrugog()!=0) {
	            	rundaIzBaze.getNatjecatelj1().setBodovi(participantIzBaze1.getBodovi() - rundaIzBaze.getBodoviPrvog());
	            	rundaIzBaze.getNatjecatelj2().setBodovi(participantIzBaze2.getBodovi() - rundaIzBaze.getBodoviDrugog());
	            }
	            if (score1 > score2) {
	            	rundaIzBaze.getNatjecatelj1().setBodovi(participantIzBaze1.getBodovi() + Double.valueOf(pobjeda));
	            	rundaIzBaze.setBodoviPrvog(Double.valueOf(pobjeda));
	            	rundaIzBaze.setBodoviDrugog(Double.valueOf(poraz));
	            } else if (score1 < score2) {
	            	rundaIzBaze.getNatjecatelj2().setBodovi(participantIzBaze2.getBodovi() + Double.valueOf(pobjeda));
	            	rundaIzBaze.setBodoviDrugog(Double.valueOf(pobjeda));
	            	rundaIzBaze.setBodoviPrvog(Double.valueOf(poraz));
	            } else {
	            	rundaIzBaze.getNatjecatelj1().setBodovi(participantIzBaze1.getBodovi() + Double.valueOf(remi));  // It's a draw
	            	rundaIzBaze.getNatjecatelj2().setBodovi(participantIzBaze2.getBodovi() + Double.valueOf(remi));
	            	rundaIzBaze.setBodoviPrvog(Double.valueOf(remi));
	            	rundaIzBaze.setBodoviDrugog(Double.valueOf(remi));
	            }
	        }
	        
	        // Spremanje azuriranog kola u bazu
	        roundService.spremiRundu(rundaIzBaze);
	       return new RedirectView("/", true);
	        
	    }
}
