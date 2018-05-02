package org.n52.spare.kicker.repositories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.n52.spare.kicker.model.Rank;
import org.n52.spare.kicker.rankings.MatchpointsKotlinAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class RankRepository {
	
	@Autowired
	private MatchRepository matchRepository;

	public Page<Rank> findAll(PageRequest page) {
		List<Rank> ranks = new MatchpointsKotlinAlgorithm().calculateForMatches(StreamSupport.stream(matchRepository.findAll().spliterator(), false)
				.collect(Collectors.toList()));
		if (page != null) {
			return new PageImpl<>(ranks.stream().skip(page.getOffset()).limit(page.getPageSize()).collect(Collectors.toList()),
					PageRequest.of((int) (page.getOffset() / page.getPageSize()), page.getPageSize()),
					ranks.size());
		}
		else {
			return new PageImpl<>(ranks);
		}
	}

}
