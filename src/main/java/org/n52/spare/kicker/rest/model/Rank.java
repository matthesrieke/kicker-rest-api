package org.n52.spare.kicker.rest.model;

import com.fasterxml.jackson.annotation.JsonView;

public class Rank {
	
	@JsonView(Views.Basic.class)
	private int rank;
	
	@JsonView(Views.Basic.class)
	private int points;
	
	@JsonView(Views.Basic.class)
	private int totalMatches;
	
	@JsonView(Views.Basic.class)
	private Player player;

	public Rank() {
	}
	
	public Rank(Player p) {
		this.player = p;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getTotalMatches() {
		return totalMatches;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setTotalMatches(int totalMatches) {
		this.totalMatches = totalMatches;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
