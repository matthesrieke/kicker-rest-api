package org.n52.spare.kicker.rest.model;

import java.util.List;

public class Timeline {

	private List<MatchEvent> events;
	
	public void setEvents(List<MatchEvent> events) {
		this.events = events;
	}
	
	public List<MatchEvent> getEvents() {
		return events;
	}
	
}
