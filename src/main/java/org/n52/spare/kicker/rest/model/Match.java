package org.n52.spare.kicker.rest.model;

public class Match {
	
	private Player home;
	private Player guest;
	private Timeline timeline;
	private Score score;
	
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
