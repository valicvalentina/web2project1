package hr.fer.web2.teamsbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import hr.fer.web2.teamsbackend.dao.ParticipantRepository;
import hr.fer.web2.teamsbackend.domain.Competition;
import hr.fer.web2.teamsbackend.domain.Participant;
import hr.fer.web2.teamsbackend.service.ParticipantService;

@Service
public class ParticipantServiceJPA implements ParticipantService {

@Autowired
private ParticipantRepository participantRepo;
@Override
public List<Participant> listAll() {
return participantRepo.findAll();
}

@Override
public Participant createParticipant(Participant participant) {
Assert.notNull(participant, "Participant object must be given");
Assert.isNull(participant.getId(),
"Participant ID must be null, not" + participant.getId());
return participantRepo.save(participant);
}

@Override
public void saveParticipants(List<Participant> participantList) {
    participantRepo.saveAll(participantList);
}

@Override
public List<Participant> findByCompetitionId(Long competitionId) {
	// TODO Auto-generated method stub
	  return participantRepo.findByCompetitionId(competitionId);
}

@Override
public Participant getParticipantById(Long long1) {
	// TODO Auto-generated method stub
	return participantRepo.findById(long1).orElse(null);
}





}