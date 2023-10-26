package hr.fer.web2.teamsbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hr.fer.web2.teamsbackend.dao.RoundRepository;
import hr.fer.web2.teamsbackend.domain.Runda;
import hr.fer.web2.teamsbackend.service.RoundService;

@Service
public class RoundServiceJPA implements RoundService{
	@Autowired
	private RoundRepository roundRepo;

	
	 public List<Runda> getAllRoundsByCompetitionId(Long competitionId) {
	        return roundRepo.findAllByCompetitionId(competitionId);
	    }
	 
	 @Override
	 public void spremiRundu(Runda runda) {
	        roundRepo.save(runda);
	    }


	@Override
	public Runda getRoundById(Long id) {
		// TODO Auto-generated method stub
		return roundRepo.findById(id).orElse(null);
	}
}
