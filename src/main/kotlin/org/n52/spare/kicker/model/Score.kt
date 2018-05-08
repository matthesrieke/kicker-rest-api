package org.n52.spare.kicker.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

import com.fasterxml.jackson.annotation.JsonView

@Entity
@Table(name = "scores")
class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @JsonView(Views.Basic::class)
    var home: Int = 0

    @JsonView(Views.Basic::class)
    var guest: Int = 0

}
