package hr.fer.web2.teamsbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hr.fer.web2.teamsbackend.domain.Participant;
import hr.fer.web2.teamsbackend.service.ParticipantService;

@RestController
@RequestMapping("/participant")
public class ParticipantController {
@Autowired
private ParticipantService participantService;
@GetMapping("")
public List<Participant> listParticipants() {
return participantService.listAll();
}
}