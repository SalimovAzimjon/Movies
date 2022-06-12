package uz.direction.movie_detail.data.model

import com.squareup.moshi.Json

data class MovieCredit(
    @field:Json(name = "cast")
    val cast: List<Cast>,
    @field:Json(name = "crew")
    val crew: List<Crew>,
    @field:Json(name = "id")
    val id: Int
)
