package hr.fer.web2.teamsbackend.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.fer.web2.teamsbackend.domain.Runda;
public interface RoundRepository extends JpaRepository<Runda, Long>
{

	List<Runda> findAllByCompetitionId(Long competitionId);



}

