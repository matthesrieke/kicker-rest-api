package org.n52.spare.kicker.rankings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.n52.spare.kicker.model.Match;
import org.n52.spare.kicker.model.Player;
import org.n52.spare.kicker.model.Rank;
import org.n52.spare.kicker.model.Score;

public class MatchpointsAlgorithm implements RankingsAlgorithm {

	private Map<Player, Rank> ranks = new HashMap<>();
	
	@Override
	public List<Rank> calculateForMatches(List<Match> matches) {
		matches.stream().forEach(m -> {
			Score score = m.getScore();
			if (score == null) {
				return;
			}
			
			Player guest = m.getGuest();
			Player home = m.getHome();
			
			plusMatch(guest);
			plusMatch(home);
			
			if (score.getGuest() == score.getHome()) {
				plusPoints(guest, 1);
				plusPoints(home, 1);
			}
			else if (score.getGuest() > score.getHome()) {
				plusPoints(guest, 3);
			}
			else {
				plusPoints(home, 3);
			}
		});
		
		return ranks.values().parallelStream()
				.sorted((a, b) -> {
					if (a.getPoints() == b.getPoints()) {
						return a.getTotalMatches() - b.getTotalMatches();
					}
					
					return a.getPoints() - b.getPoints();
				})
				.collect(Collectors.toList());
	}

	private void plusMatch(Player p) {
		Rank r;
		if (ranks.containsKey(p)) {
			r = ranks.get(p);
		}
		else {
			r = new Rank();
			r.setPlayer(p);
			ranks.put(p, r);		
		}
		
		r.setTotalMatches(r.getTotalMatches() + 1);
	}

	private void plusPoints(Player p, int pointsToAdd) {
		if (ranks.containsKey(p)) {
			Rank r = ranks.get(p);
			r.setPoints(r.getPoints() + pointsToAdd);
		}
	}

}
