package org.n52.spare.kicker.repositories;

import org.n52.spare.kicker.model.Match;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {

}
