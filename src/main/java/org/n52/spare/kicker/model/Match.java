package org.n52.spare.kicker.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@Column(nullable = false)
	private Date dateTime;
    
    @JsonView(Views.Basic.class)
    @ManyToOne(optional = false)
	private Player home;
    
    @JsonView(Views.Basic.class)
    @ManyToOne(optional = false)
	private Player guest;
	
    @JsonView(Views.Basic.class)
    @OneToMany(cascade = CascadeType.ALL)
	private List<MatchEvent> events;
	
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
	
	public List<MatchEvent> getEvents() {
		return events;
	}
	
	public void setEvents(List<MatchEvent> events) {
		this.events = events;
	}
	
	public Score getScore() {
		return score;
	}
	
	public void setScore(Score score) {
		this.score = score;
	}
	
	public Date getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
