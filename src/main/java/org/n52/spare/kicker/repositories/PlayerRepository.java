package org.n52.spare.kicker.repositories;

import java.util.Optional;

import org.n52.spare.kicker.model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
	
	@Query("select p from Player p where lower(nickName) = lower(:name)")
	Optional<Player> findByName(@Param("name") String name);

}
