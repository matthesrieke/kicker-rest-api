package org.n52.spare.kicker.rest.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "timelines")
public class Timeline {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany(targetEntity=MatchEvent.class, mappedBy="match", fetch=FetchType.EAGER)
	private List<MatchEvent> events;
	
	public void setEvents(List<MatchEvent> events) {
		this.events = events;
	}
	
	public List<MatchEvent> getEvents() {
		return events;
	}
	
}
