package org.n52.spare.kicker.rest.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.n52.spare.kicker.rest.model.Match;
import org.n52.spare.kicker.rest.model.MatchEvent;
import org.n52.spare.kicker.rest.model.Player;
import org.n52.spare.kicker.rest.model.Score;
import org.n52.spare.kicker.rest.model.Views;
import org.n52.spare.kicker.rest.repositories.MatchRepository;
import org.n52.spare.kicker.rest.repositories.PlayerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/matches")
public class MatchesController implements InitializingBean {
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private PlayerRepository playerRepo;
	
	
	@JsonView(Views.Basic.class)
	@RequestMapping("")
	public List<Match> collection(@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) Optional<Integer> size) {
		return matchRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(10))).getContent();
	}
	
	@JsonView(Views.Details.class)
	@RequestMapping(params = "expanded=true")
	public List<Match> collectionWithDetails(@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) Optional<Integer> size) {
		return matchRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(10))).getContent();
	}
	
	@JsonView(Views.Details.class)
	@RequestMapping("/{id}")
	public Match single(@PathVariable Long id) {
		return matchRepository.findById(id).get();
	}
	
	@JsonView(Views.Basic.class)
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Match createMatch(@RequestBody Match match) {
		if (match != null) {
			if (match.getId() != null) {
				throw new IllegalArgumentException("'id' must not be provided");
			}
			
			if (match.getDateTime() == null) {
				match.setDateTime(new Date());
			}
			
			Match result = matchRepository.save(match);
			return result;
		}
		throw new IllegalArgumentException("no match object found");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
//		insertDummyData();
	}
	
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
		m.setDateTime(new Date());
		m.setHome(p2);
		m.setGuest(p1);
		Score s = new Score();
		s.setGuest(6);
		s.setHome(3);
		m.setScore(s);
		
		List<MatchEvent> events = new ArrayList<>();
		MatchEvent e1 = new MatchEvent();
		e1.setDateTime(new Date());
		e1.setGuestScore(1);
		events.add(e1);
		e1.setMatch(m);
		MatchEvent e2 = new MatchEvent();
		e2.setDateTime(new Date(e1.getDateTime().getTime() + 10000));
		e2.setFulltime(true);
		e2.setMatch(m);
		events.add(e2);
		
		m.setEvents(events);
		
		matchRepository.save(m);
	}

}
