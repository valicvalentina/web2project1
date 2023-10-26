package hr.fer.web2.teamsbackend.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
@Entity
public class Participant {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private Double bodovi;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

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

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Double getBodovi() {
		return bodovi;
	}

	public void setBodovi(Double bodovi) {
		this.bodovi = bodovi;
	}

}

