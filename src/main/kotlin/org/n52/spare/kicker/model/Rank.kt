package org.n52.spare.kicker.model

import com.fasterxml.jackson.annotation.JsonView

class Rank {

    @JsonView(Views.Basic::class)
    var rank: Int = 0

    @JsonView(Views.Basic::class)
    var points: Int = 0

    @JsonView(Views.Basic::class)
    var totalMatches: Int = 0

    @JsonView(Views.Basic::class)
    var player: Player? = null

    constructor() {}

    constructor(p: Player) {
        this.player = p
    }

}
