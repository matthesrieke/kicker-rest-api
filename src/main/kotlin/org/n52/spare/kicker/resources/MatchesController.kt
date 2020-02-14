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
import org.springframework.data.domain.Sort
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@RestController
@RequestMapping("/matches")
class MatchesController : InitializingBean {

    @Autowired
    private val matchRepository: MatchRepository? = null

    @Autowired
    private val playerRepository: PlayerRepository? = null

    @JsonView(Views.Basic::class)
    @RequestMapping("")
    fun collection(@RequestParam(required = false) page: Optional<Int>,
                   @RequestParam(required = false) size: Optional<Int>): PageableResponse<Match> {
        val pageable = matchRepository!!.findAll(PageRequest.of(page.orElse(0), size.orElse(10), Sort.by(Sort.Direction.DESC,"dateTime")))
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
    fun single(@PathVariable id: Long?): Match {
        return matchRepository!!.findById(id!!).get()
    }

    @JsonView(Views.Details::class)
    @RequestMapping("/{id}/approve")
    fun approve(@PathVariable id: Long?): Match {
        return matchRepository!!.findById(id!!).get()
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

            this.resolvePlayers(match);

            return matchRepository!!.save<Match?>(match)
        }
        throw IllegalArgumentException("no match object found")
    }

    fun resolvePlayers(match: Match): Match {
        val guest = playerRepository!!.findByName(match.guest.nickName);
        val home = playerRepository!!.findByName(match.home.nickName);

        if (!guest.isPresent) {
            throw IllegalArgumentException("Guest player '" + match.guest.nickName + "' not found");
        }

        if (!home.isPresent) {
            throw IllegalArgumentException("Home player '" + match.home.nickName + "' not found");
        }

        match.guest = guest.get();
        match.home = home.get();

        return match;
    }

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
//        insertDummyData()
    }

    fun insertDummyData() {
//        matchRepository!!.deleteAll()
//        playerRepository!!.deleteAll()
//
//        val p1 = Player();
//        p1.nickName = "Mathijsen"
//        p1.firstName = "Matthes"
//        p1.lastName = "Rieke"
//        p1.password = BCryptPasswordEncoder().encode("asdf")
//
//
//        val p2 = Player();
//        p2.nickName = "Staschinho"
//        p2.firstName = "Christoph"
//        p2.lastName = "Stasch"
//        p2.password = BCryptPasswordEncoder().encode("asdf")
//
//        playerRepository!!.save(p1);
//        playerRepository!!.save(p2);

        val p1 = playerRepository!!.findByName("Mathijsen")
        val p2 = playerRepository!!.findByName("Staschinho")

        val m = Match()
        m.dateTime = Date()
        m.home = p2.get()
        m.guest = p1.get()

        val s = Score()
        s.guest = 6
        s.home = 0
        m.score = s

        val events = mutableListOf<MatchEvent>()
        val e1 = MatchEvent()
        e1.dateTime = Date()
        e1.guestScore = 1
        events.add(e1)
        e1.match = m

        val e2 = MatchEvent()
        e2.dateTime = Date(e1.dateTime.time + 10000)
        e2.fulltime = true
        e2.match = m
        events.add(e2)

        m.events = events

        matchRepository!!.save(m)
    }

}
