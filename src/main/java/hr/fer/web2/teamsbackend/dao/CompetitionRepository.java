package hr.fer.web2.teamsbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.fer.web2.teamsbackend.domain.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long>{

	List<Competition> findByCreatedBy(String createdBy);

}
