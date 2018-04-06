package org.n52.spare.kicker.rest.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "matches")
public class Match {
	
	@JsonView(Views.Basic.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JsonView(Views.Basic.class)
    @ManyToOne
	private Player home;
    
    @JsonView(Views.Basic.class)
    @ManyToOne
	private Player guest;
	
    @JsonView(Views.Details.class)
    @OneToOne(cascade = CascadeType.ALL)
	private Timeline timeline;
	
    @JsonView(Views.Basic.class)
	@OneToOne(cascade = CascadeType.ALL)
	private Score score;
	
	public Long getId() {
		return id;
	}
	
	public Player getHome() {
		return home;
	}
	
	public void setHome(Player home) {
		this.home = home;
	}
	
	public Player getGuest() {
		return guest;
	}
	
	public void setGuest(Player guest) {
		this.guest = guest;
	}
	
	public Timeline getTimeline() {
		return timeline;
	}
	
	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
	
	public Score getScore() {
		return score;
	}
	
	public void setScore(Score score) {
		this.score = score;
	}

}
