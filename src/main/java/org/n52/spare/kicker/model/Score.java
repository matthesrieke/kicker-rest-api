package org.n52.spare.kicker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JsonView(Views.Basic.class)
	private int home;
    
    @JsonView(Views.Basic.class)
	private int guest;
	
	public void setHome(int home) {
		this.home = home;
	}
	
	public int getHome() {
		return home;
	}
	
	public void setGuest(int guest) {
		this.guest = guest;
	}
	
	public int getGuest() {
		return guest;
	}
	
}
