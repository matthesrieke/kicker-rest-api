package org.n52.spare.kicker.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "players")
public class Player {
	
	@JsonView(Views.Basic.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	@JsonView(Views.Basic.class)
	private String nickName;
	
	@JsonView(Views.Details.class)
	private String firstName;
	
	@JsonView(Views.Details.class)
	private String lastName;
	
	public Long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
