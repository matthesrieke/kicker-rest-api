package org.n52.spare.kicker.rankings

import org.n52.spare.kicker.model.Match
import org.n52.spare.kicker.model.Rank

interface RankingsAlgorithm {

    fun calculateForMatches(matches: List<Match>): List<Rank>

}
