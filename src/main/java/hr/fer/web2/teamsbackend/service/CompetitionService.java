package hr.fer.web2.teamsbackend.service;

import java.util.List;

import hr.fer.web2.teamsbackend.domain.Competition;

public interface CompetitionService {
	List<Competition> listAll();
	Competition createCompetition(Competition competition);
	Competition getCompetitionById(Long id);
	List<Competition> findByCreatedBy(String createdBy);
}
