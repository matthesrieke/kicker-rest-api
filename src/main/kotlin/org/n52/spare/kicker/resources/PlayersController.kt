package org.n52.spare.kicker.resources

import java.security.Principal
import java.util.Optional

import org.n52.spare.kicker.model.Player
import org.n52.spare.kicker.model.Views
import org.n52.spare.kicker.repositories.PlayerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import com.fasterxml.jackson.annotation.JsonView

@RestController
@RequestMapping("/players")
class PlayersController {

    @Autowired
    private val playerRepository: PlayerRepository? = null

    @JsonView(Views.Basic::class)
    @RequestMapping("")
    fun collection(@RequestParam(required = false) page: Optional<Int>,
                   @RequestParam(required = false) size: Optional<Int>): List<Player> {
        return playerRepository!!.findAll(PageRequest.of(page.orElse(0), size.orElse(10))).content
    }

    @JsonView(Views.Details::class)
    @RequestMapping("/{id}")
    fun single(@PathVariable id: Long?): Player {
        return playerRepository!!.findById(id!!).get()
    }

    @RequestMapping("/me")
    fun user(user: Principal?): Player {
        if (user != null) {
            val opt = playerRepository!!.findByName(user.name)
            if (opt.isPresent) {
                return opt.get()
            }
        }

        throw IllegalStateException("Invalid user")
    }
}
