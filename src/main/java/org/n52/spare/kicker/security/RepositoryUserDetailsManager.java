package org.n52.spare.kicker.security;

import java.util.Optional;

import org.n52.spare.kicker.model.Player;
import org.n52.spare.kicker.repositories.PlayerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

public class RepositoryUserDetailsManager implements UserDetailsManager {
	
	private PlayerRepository playerRepo;
	
	private PasswordEncoder passwordEncoder;

	public RepositoryUserDetailsManager(PlayerRepository repo, PasswordEncoder enc) {
		this.playerRepo = repo;
		this.passwordEncoder = enc;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Player> opt = this.playerRepo.findByName(username);
		if (opt.isPresent()) {
			return fromPlayer(opt.get());
		}
		throw new UsernameNotFoundException("User not found: " + username);
	}

	@Override
	public void createUser(UserDetails user) {
	}

	@Override
	public void updateUser(UserDetails user) {
	}

	@Override
	public void deleteUser(String username) {
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			UserDetails details = (UserDetails) auth.getPrincipal();
			if (!passwordEncoder.matches(oldPassword, details.getPassword())) {
				throw new IllegalStateException("Old password did not match");
			}
			
			Optional<Player> playerOpt = playerRepo.findByName(details.getUsername());
			if (playerOpt.isPresent()) {
				Player player = playerOpt.get();
				player.setPassword(passwordEncoder.encode(newPassword));
				playerRepo.save(player);
			}
		}
	}

	@Override
	public boolean userExists(String username) {
		Optional<Player> opt = this.playerRepo.findByName(username);
		return opt.isPresent();
	}
	
	private UserDetails fromPlayer(Player p) {
		UserDetails details = User.builder()
				.username(p.getNickName())
				.password(p.getPassword())
				.roles("USER")
				.build();
		return details;
	}
	

}
