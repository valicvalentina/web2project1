package hr.fer.web2.teamsbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import hr.fer.web2.teamsbackend.dao.CompetitionRepository;
import hr.fer.web2.teamsbackend.domain.Competition;
import hr.fer.web2.teamsbackend.service.CompetitionService;

@Service
public class CompetitionServiceJPA implements CompetitionService {

@Autowired
private CompetitionRepository competitionRepo;
@Override
public List<Competition> listAll() {
return competitionRepo.findAll();
}

@Override
public Competition createCompetition(Competition competition) {
Assert.notNull(competition, "Competition object must be given");
Assert.isNull(competition.getId(),
"Student ID must be null, not" + competition.getId()
);
return competitionRepo.save(competition);
}
@Override
public Competition getCompetitionById(Long id) {
    return competitionRepo.findById(id).orElse(null);
}

@Override
public List<Competition> findByCreatedBy(String createdBy) {
	 return competitionRepo.findByCreatedBy(createdBy);
}
}
