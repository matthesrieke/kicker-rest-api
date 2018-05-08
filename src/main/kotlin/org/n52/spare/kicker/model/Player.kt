package org.n52.spare.kicker.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

import com.fasterxml.jackson.annotation.JsonView

@Entity
@Table(name = "players")
class Player {

    @JsonView(Views.Basic::class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    @JsonView(Views.Basic::class)
    @Column(unique = true)
    var nickName: String? = null

    @JsonView(Views.Details::class)
    var firstName: String? = null

    @JsonView(Views.Details::class)
    var lastName: String? = null

    var email: String? = null

    @Column(nullable = false)
    var password: String? = null

}
