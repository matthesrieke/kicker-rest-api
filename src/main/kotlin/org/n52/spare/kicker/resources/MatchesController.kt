package org.n52.spare.kicker.resources

import java.util.Date
import java.util.Optional

import org.n52.spare.kicker.model.Match
import org.n52.spare.kicker.model.PageableResponse
import org.n52.spare.kicker.model.Views
import org.n52.spare.kicker.repositories.MatchRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import com.fasterxml.jackson.annotation.JsonView

@RestController
@RequestMapping("/matches")
class MatchesController : InitializingBean {

    @Autowired
    private val matchRepository: MatchRepository? = null

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

            return matchRepository!!.save<Match?>(match)
        }
        throw IllegalArgumentException("no match object found")
    }

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        insertDummyData()
    }

    fun insertDummyData() {
        //		Player p1 = new Player();
        //		p1.setNickName("Mathijsen");
        //		p1.setFirstName("Matthes");
        //		p1.setLastName("Rieke");
        //
        //		Player p2 = new Player();
        //		p2.setNickName("Staschinho");
        //		p2.setFirstName("Christoph");
        //		p2.setLastName("Stasch");
        //
        //		playerRepo.save(p1);
        //		playerRepo.save(p2);
        //
        //		Match m = new Match();
        //		m.setDateTime(new Date());
        //		m.setHome(p2);
        //		m.setGuest(p1);
        //		Score s = new Score();
        //		s.setGuest(6);
        //		s.setHome(3);
        //		m.setScore(s);
        //
        //		List<MatchEvent> events = new ArrayList<>();
        //		MatchEvent e1 = new MatchEvent();
        //		e1.setDateTime(new Date());
        //		e1.setGuestScore(1);
        //		events.add(e1);
        //		e1.setMatch(m);
        //		MatchEvent e2 = new MatchEvent();
        //		e2.setDateTime(new Date(e1.getDateTime().getTime() + 10000));
        //		e2.setFulltime(true);
        //		e2.setMatch(m);
        //		events.add(e2);
        //
        //		m.setEvents(events);
        //
        //		matchRepository.save(m);
    }

}
