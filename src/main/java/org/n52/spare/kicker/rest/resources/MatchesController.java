package org.n52.spare.kicker.rest.resources;

import java.util.Collections;
import java.util.List;

import org.n52.spare.kicker.rest.model.Match;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matches")
public class MatchesController {
	
	@RequestMapping("")
	public List<Match> collection() {
		return Collections.emptyList();
	}
	

}
