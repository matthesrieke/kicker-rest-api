package org.n52.spare.kicker.rest.repositories;

import org.n52.spare.kicker.rest.model.Player;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

}
