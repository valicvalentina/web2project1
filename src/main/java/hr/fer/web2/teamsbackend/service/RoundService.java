package hr.fer.web2.teamsbackend.service;

import java.util.List;
import java.util.Optional;

import hr.fer.web2.teamsbackend.domain.Runda;

public interface RoundService {
	List<Runda> getAllRoundsByCompetitionId(Long competitionId);
	void spremiRundu(Runda runda);
	Runda getRoundById(Long id);
}
