package org.n52.spare.kicker.rest.repositories;

import org.n52.spare.kicker.rest.model.MatchEvent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchEventRepository extends PagingAndSortingRepository<MatchEvent, Long> {

}
