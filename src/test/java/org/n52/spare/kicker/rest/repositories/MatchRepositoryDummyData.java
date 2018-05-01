package org.n52.spare.kicker.rest.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.n52.spare.kicker.model.Match;
import org.n52.spare.kicker.model.Player;
import org.n52.spare.kicker.model.Score;
import org.n52.spare.kicker.repositories.MatchRepository;
import org.n52.spare.kicker.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@PropertySource("same-h2-application.yml")
public class MatchRepositoryDummyData {
	
	@Autowired
	private MatchRepository repo;
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Test
	public void insertDummyData() {
		Player p1 = new Player();
		p1.setNickName("Mathijsen");
		p1.setFirstName("Matthes");
		p1.setLastName("Rieke");
		
		Player p2 = new Player();
		p2.setNickName("Staschinho");
		p2.setFirstName("Christoph");
		p2.setLastName("Stasch");
		
		playerRepo.save(p1);
		playerRepo.save(p2);
		
		
		Match m = new Match();
		m.setHome(p2);
		m.setGuest(p1);
		Score s = new Score();
		s.setGuest(6);
		s.setHome(3);
		m.setScore(s);
		
		repo.save(m);
	}
	
}
