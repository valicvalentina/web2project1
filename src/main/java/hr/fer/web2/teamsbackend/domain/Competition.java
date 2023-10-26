package hr.fer.web2.teamsbackend.domain;


import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String scoringSystem;
    private String createdBy; 
    private boolean rounds;
    
   @JsonIgnore
    @OneToMany(mappedBy = "competition")
    private List<Participant> participants;
    
    
    private String participantsStr;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScoringSystem() {
		return scoringSystem;
	}

	public void setScoringSystem(String scoringSystem) {
		this.scoringSystem = scoringSystem;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	  public String getParticipantsStr() {
			return participantsStr;
		}

		public void setParticipantsStr(String participantsStr) {
			this.participantsStr = participantsStr;
		}

	public boolean isRounds() {
		return rounds;
	}

	public void setRounds(boolean rounds) {
		this.rounds = rounds;
	}

}

