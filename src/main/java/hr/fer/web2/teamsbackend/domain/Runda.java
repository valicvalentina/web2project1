package hr.fer.web2.teamsbackend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
	public class Runda {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		
	    private int brojKola;
	    public double getBodoviPrvog() {
			return bodoviPrvog;
		}

		public void setBodoviPrvog(double bodoviPrvog) {
			this.bodoviPrvog = bodoviPrvog;
		}

		public double getBodoviDrugog() {
			return bodoviDrugog;
		}

		public void setBodoviDrugog(double bodoviDrugog) {
			this.bodoviDrugog = bodoviDrugog;
		}

		private double bodoviPrvog;
	    private double bodoviDrugog;
	    
	    @ManyToOne
	    @JoinColumn(name = "competition_id")
	    private Competition competition;
	    
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Competition getCompetition() {
			return competition;
		}

		public void setCompetition(Competition competition) {
			this.competition = competition;
		}

		@ManyToOne
	    @JoinColumn(name = "participant1_id")
	    private Participant natjecatelj1;
	    
	    @ManyToOne
	    @JoinColumn(name = "participant2_id")
	    private Participant natjecatelj2;
	    
        private String rezultat;
	    public String getRezultat() {
			return rezultat;
		}

		public void setRezultat(String rezultat) {
			this.rezultat = rezultat;
		}
		public Runda() {
		 
		}
		public Runda(int brojKola, Participant natjecatelj1, Participant natjecatelj2) {
	        this.setBrojKola(brojKola);
	        this.setNatjecatelj1(natjecatelj1);
	        this.setNatjecatelj2(natjecatelj2);
	    
	    }

		public Participant getNatjecatelj1() {
			return natjecatelj1;
		}

		public void setNatjecatelj1(Participant natjecatelj1) {
			this.natjecatelj1 = natjecatelj1;
		}

		public Participant getNatjecatelj2() {
			return natjecatelj2;
		}

		public void setNatjecatelj2(Participant natjecatelj2) {
			this.natjecatelj2 = natjecatelj2;
		}

		public int getBrojKola() {
			return brojKola;
		}

		public void setBrojKola(int brojKola) {
			this.brojKola = brojKola;
		}
	}


