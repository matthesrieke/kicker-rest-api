package org.n52.spare.kicker.resources

import java.util.Date
import java.util.Optional

import org.n52.spare.kicker.repositories.MatchRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import com.fasterxml.jackson.annotation.JsonView
import org.n52.spare.kicker.model.*
import org.n52.spare.kicker.repositories.PlayerRepository
import org.n52.spare.kicker.security.RepositoryUserDetailsManager
import org.n52.spare.kicker.security.WebSecurityConfig
import org.springframework.http.HttpMethod
import org.springframework.security.core.Authentication

@RestController
@RequestMapping("/matches")
class MatchesController : InitializingBean {

    @Autowired
    private val matchRepository: MatchRepository? = null

    @Autowired
    private val playerRepo: PlayerRepository? = null

    @Autowired
    private val security: WebSecurityConfig? = null

    @JsonView(Views.Basic::class)
    @RequestMapping("")
    fun collection(@RequestParam(required = false) page: Optional<Int>,
                   @RequestParam(required = false) size: Optional<Int>): PageableResponse<Match> {
        val pageable = matchRepository!!.findAll(PageRequest.of(page.orElse(0), size.orElse(10)))
        return PageableResponse.from(pageable)
    }

    @JsonView(Views.Details::class)
    @RequestMapping(params = arrayOf("expanded=true"))
    fun collectionWithDetails(@RequestParam(required = false) page: Optional<Int>,
                              @RequestParam(required = false) size: Optional<Int>): PageableResponse<Match> {
        return collection(page, size)
    }

    @JsonView(Views.Details::class)
    @RequestMapping("/{id}")
    fun single(@PathVariable id: Long): Match {
        return matchRepository!!.findById(id).get()
    }

    @JsonView(Views.Details::class)
    @RequestMapping("/{id}/approve", method = [RequestMethod.PUT])
    fun approve(@PathVariable id: Long, auth: Authentication): Match {
        var match = matchRepository!!.findById(id).get()

        var requestingPlayer = (security!!.userDetailsService() as RepositoryUserDetailsManager).playerFromAuthentication(auth)

        when {
            match?.guest?.id == requestingPlayer.id -> match.guestApproved = true
            match?.home?.id == requestingPlayer.id -> match.homeApproved = true
            else -> throw IllegalArgumentException("Not allowed to approve match")
        }

        matchRepository!!.save(match)

        return match
    }

    @JsonView(Views.Basic::class)
    @RequestMapping(value = "", method = arrayOf(RequestMethod.POST))
    fun createMatch(@RequestBody match: Match?): Match {
        if (match != null) {
            if (match.id != null) {
                throw IllegalArgumentException("'id' must not be provided")
            }

            if (match.dateTime == null) {
                match.dateTime = Date()
            }

            return matchRepository!!.save<Match?>(match)
        }
        throw IllegalArgumentException("no match object found")
    }

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        insertDummyData()
    }

    fun insertDummyData() {
//        val p1 = Player();
//        p1.nickName = "Mathijsen";
//        p1.firstName = "Matthes";
//        p1.lastName = "Rieke";
//        p1.password = "\$2a\$10\$sLqDPwTQ9UQT4zEzRyPIJeuQqgjRoJm6OXlYy6akFwQLCAqLHOzqq";
//
//        val p2 = Player();
//        p2.nickName = "Staschinho";
//        p2.firstName = "Christoph";
//        p2.lastName = "Stasch";
//        p2.password = "\$2a\$10\$sLqDPwTQ9UQT4zEzRyPIJeuQqgjRoJm6OXlYy6akFwQLCAqLHOzqq";
//
//        playerRepo!!.save(p1);
//        playerRepo!!.save(p2);
//
//        val m = Match();
//        m.dateTime = Date();
//        m.home = p2;
//        m.guest = p1;
//        m.guestApproved = true;
//        val s = Score();
//        s.guest = 6;
//        s.home = 3;
//        m.score = s;
//        m.homeApproved = false;
//
//        val events = ArrayList<MatchEvent>();
//        val e1 = MatchEvent();
//        e1.dateTime = Date();
//        e1.guestScore = 1;
//        events.add(e1);
//        e1.match = m;
//        val e2 = MatchEvent();
//        e2.dateTime = Date(e1.dateTime!!.getTime() + 10000);
//        e2.fulltime = true;
//        e2.match = m;
//        events.add(e2);
//
//        m.events = events;
//
//        matchRepository!!.save(m);
    }

}
