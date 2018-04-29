package rankings;

import java.util.List;

import org.n52.spare.kicker.rest.model.Match;
import org.n52.spare.kicker.rest.model.Rank;

public interface RankingsAlgorithm {
	
	List<Rank> calculateForMatches(List<Match> matches);

}
