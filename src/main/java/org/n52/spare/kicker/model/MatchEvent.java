package org.n52.spare.kicker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "match_events")
public class MatchEvent {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Match match;
    
    @JsonView(Views.Basic.class)
    @Column(nullable = false)
    private Date dateTime;
    
    @JsonView(Views.Basic.class)
    private Integer homeScore;
    
    @JsonView(Views.Basic.class)
    private Integer guestScore;
    
    @JsonView(Views.Basic.class)
    private Boolean halftime;
    
    @JsonView(Views.Basic.class)
    private Boolean fulltime;
    
    @JsonView(Views.Basic.class)
    private Boolean overtime;
    
    @JsonView(Views.Basic.class)
    private Boolean finished;
    
    @JsonView(Views.Basic.class)
    private Boolean pause;
    
    @JsonView(Views.Basic.class)
    private Boolean resume;
    
    @JsonView(Views.Basic.class)
    private Boolean cancel;
    
    public void setMatch(Match match) {
		this.match = match;
	}
    
    public Match getMatch() {
		return match;
	}

	public Long getId() {
		return id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getHalftime() {
		return halftime;
	}

	public void setHalftime(Boolean halftime) {
		this.halftime = halftime;
	}

	public Boolean getFulltime() {
		return fulltime;
	}

	public void setFulltime(Boolean fulltime) {
		this.fulltime = fulltime;
	}

	public Boolean getPause() {
		return pause;
	}

	public void setPause(Boolean pause) {
		this.pause = pause;
	}

	public Boolean getResume() {
		return resume;
	}

	public void setResume(Boolean resume) {
		this.resume = resume;
	}

	public Boolean getCancel() {
		return cancel;
	}

	public void setCancel(Boolean cancel) {
		this.cancel = cancel;
	}

	public Integer getGuestScore() {
		return guestScore;
	}

	public void setGuestScore(Integer guestScore) {
		this.guestScore = guestScore;
	}

	public Integer getHomeScore() {
		return homeScore;
	}
	
	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}
	
	public Boolean getOvertime() {
		return overtime;
	}
	
	public void setOvertime(Boolean overtime) {
		this.overtime = overtime;
	}
	
	public Boolean getFinished() {
		return finished;
	}
	
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	
}
