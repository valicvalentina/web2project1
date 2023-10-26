package hr.fer.web2.teamsbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.fer.web2.teamsbackend.domain.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>
{

	List<Participant> findByCompetitionId(Long competitionId);

}
