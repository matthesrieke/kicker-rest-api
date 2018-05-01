package org.n52.spare.kicker.rankings;

import java.util.List;

import org.n52.spare.kicker.model.Match;
import org.n52.spare.kicker.model.Rank;

public interface RankingsAlgorithm {
	
	List<Rank> calculateForMatches(List<Match> matches);

}
