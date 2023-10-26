package hr.fer.web2.teamsbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.web2.teamsbackend.RasporedGenerator;
import hr.fer.web2.teamsbackend.domain.Competition;
import hr.fer.web2.teamsbackend.domain.Participant;
import hr.fer.web2.teamsbackend.domain.Runda;
import hr.fer.web2.teamsbackend.service.CompetitionService;
import hr.fer.web2.teamsbackend.service.ParticipantService;

import org.springframework.web.servlet.view.RedirectView;

import ch.qos.logback.core.model.Model;

@RestController
@RequestMapping("/competition")
public class CompetitionController {
	
@Autowired
private CompetitionService competitionService;
@Autowired
private ParticipantService participantService;

@Autowired
private RasporedGenerator rasporedGenerator;


@GetMapping("")
public List<Competition> listCompetitions() {
return competitionService.listAll();
}

@PostMapping("")
public RedirectView createCompetition(@ModelAttribute Competition competition,@RequestParam(name = "participants", required = false) String participants, Authentication authentication) {
	 String createdBy = authentication.getName();
	 competition.setCreatedBy(createdBy);
	 competition.setParticipantsStr(participants);
	  System.out.println("Received competition data: " + competition);
	  competition.setRounds(true);
	  competitionService.createCompetition(competition);
	  if (participants != null && !participants.isEmpty()) {
	        String[] participantNames = participants.split(";");
	        List<Participant> participantList = new ArrayList<>();
	        for (String participantName : participantNames) {
	            // Stvaranje sudionika i dodavanje u listu
	            Participant participant = new Participant();
	            participant.setName(participantName);
	            participant.setCompetition(competition); // Postavljanje competition_id
	            participantList.add(participant);
	            participant.setBodovi(0.0);
	        }
	        // Spremanje listu sudionika u bazu
	        participantService.saveParticipants(participantList);
	    }
	  
return new RedirectView("/", true);

}

}