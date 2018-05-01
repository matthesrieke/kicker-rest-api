package org.n52.spare.kicker.resources;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.n52.spare.kicker.model.Player;
import org.n52.spare.kicker.model.Views;
import org.n52.spare.kicker.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/players")
public class PlayersController {

	@Autowired
	private PlayerRepository playerRepository;

	@JsonView(Views.Basic.class)
	@RequestMapping("")
	public List<Player> collection(@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) Optional<Integer> size) {
		return playerRepository.findAll(PageRequest.of(page.orElse(0), size.orElse(10))).getContent();
	}

	@JsonView(Views.Details.class)
	@RequestMapping("/{id}")
	public Player single(@PathVariable Long id) {
		return playerRepository.findById(id).get();
	}

	@RequestMapping("/me")
	public Player user(Principal user) {
		if (user != null) {
			Optional<Player> opt = playerRepository.findByName(user.getName());
			if (opt.isPresent()) {
				return opt.get();
			}
		}
		
		throw new IllegalStateException("Invalid user");
	}
}