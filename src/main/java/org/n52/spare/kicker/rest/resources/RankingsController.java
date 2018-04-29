package org.n52.spare.kicker.rest.resources;

import java.util.Optional;

import org.n52.spare.kicker.rest.model.PageableResponse;
import org.n52.spare.kicker.rest.model.Rank;
import org.n52.spare.kicker.rest.model.Views;
import org.n52.spare.kicker.rest.repositories.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/rankings")
public class RankingsController {
	
	@Autowired
	private RankRepository rankRepository;
	
	
	@JsonView(Views.Basic.class)
	@RequestMapping("")
	public PageableResponse<Rank> collection(@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) Optional<Integer> size) {
		Page<Rank> pageable = rankRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(10)));
		return PageableResponse.from(pageable);
	}

}
