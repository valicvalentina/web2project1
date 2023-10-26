package hr.fer.web2.teamsbackend.service;

import java.util.List;

import hr.fer.web2.teamsbackend.domain.Competition;
import hr.fer.web2.teamsbackend.domain.Participant;

public interface ParticipantService {
	List<Participant> listAll();
	Participant createParticipant(Participant participant);
	void saveParticipants(List<Participant> participantList);
	List<Participant> findByCompetitionId(Long competitionId);
	Participant getParticipantById(Long long1);
}
