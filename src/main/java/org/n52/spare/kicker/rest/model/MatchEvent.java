package org.n52.spare.kicker.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "match_events")
public abstract class MatchEvent {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Match match;
    
    public void setMatch(Match match) {
		this.match = match;
	}
    
    public Match getMatch() {
		return match;
	}

}
