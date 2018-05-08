package org.n52.spare.kicker.model

import org.springframework.data.domain.Page

import com.fasterxml.jackson.annotation.JsonView

class PageableResponse<T> {

    @JsonView(Views.Basic::class)
    var total: Int? = null

    @JsonView(Views.Basic::class)
    var page: Int? = null

    @JsonView(Views.Basic::class)
    var size: Int? = null

    @JsonView(Views.Basic::class)
    var data: List<T>? = null

    companion object {

        fun <T> from(pageable: Page<T>): PageableResponse<T> {
            val response = PageableResponse<T>()
            response.data = pageable.content
            response.page = pageable.number
            response.size = pageable.size
            response.total = pageable.totalPages * pageable.size
            return response
        }
    }

}
