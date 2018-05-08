package org.n52.spare.kicker.rankings

import org.n52.spare.kicker.model.Match
import org.n52.spare.kicker.model.Rank
import org.n52.spare.kicker.model.Score
import org.n52.spare.kicker.model.Player
import org.n52.spare.kicker.rankings.RankingsAlgorithm

class MatchpointsAlgorithmImpl : RankingsAlgorithm {
	
	private val ranks: MutableMap<Player, Rank> = mutableMapOf()
	
	override fun calculateForMatches(matches: List<Match>): List<Rank> {
		if (matches == null) return mutableListOf<Rank>()
		
		matches.forEach{m ->
			if (m.score == null) return@forEach
			
			val guest = m.guest!!
			val home = m.home!!
			
			plusMatch(guest)
			plusMatch(home)
			
			if (m.score!!.guest == m.score!!.home) {
				plusPoints(guest, 1)
				plusPoints(home, 1)
			}
			else if (m.score!!.guest > m.score!!.home) {
				plusPoints(guest, 3)
			}
			else {
				plusPoints(home, 3)
			}
		}
		
		val result = ArrayList(ranks.values)
		return result.sortedWith(compareByDescending<Rank>{it.points}.thenBy{it.totalMatches})
	}
	
	private fun plusMatch(player: Player) {
		val r: Rank
		if (ranks.containsKey(player)) {
			r = ranks.get(player)!!
		}
		else {
			r = Rank()
			r.player = player
			ranks.put(player, r)
		}
		
		r.totalMatches += 1
	}
	
	private fun plusPoints(player: Player, points: Int) {
		if (ranks.containsKey(player)) {
			val r = ranks.get(player)!!
			r.points += points
		}
	}
	
}

