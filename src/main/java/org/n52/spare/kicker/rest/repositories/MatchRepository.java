package org.n52.spare.kicker.rest.repositories;

import org.n52.spare.kicker.rest.model.Match;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {

}
