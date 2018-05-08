package org.n52.spare.kicker.model

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

import com.fasterxml.jackson.annotation.JsonView

@Entity
@Table(name = "match_events")
class MatchEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    @ManyToOne
    var match: Match? = null

    @JsonView(Views.Basic::class)
    @Column(nullable = false)
    var dateTime: Date? = null

    @JsonView(Views.Basic::class)
    var homeScore: Int? = null

    @JsonView(Views.Basic::class)
    var guestScore: Int? = null

    @JsonView(Views.Basic::class)
    var halftime: Boolean? = null

    @JsonView(Views.Basic::class)
    var fulltime: Boolean? = null

    @JsonView(Views.Basic::class)
    var overtime: Boolean? = null

    @JsonView(Views.Basic::class)
    var finished: Boolean? = null

    @JsonView(Views.Basic::class)
    var pause: Boolean? = null

    @JsonView(Views.Basic::class)
    var resume: Boolean? = null

    @JsonView(Views.Basic::class)
    var cancel: Boolean? = null

}
