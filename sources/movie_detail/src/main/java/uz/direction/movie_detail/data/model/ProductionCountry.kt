package uz.direction.movie_detail.data.model

import com.squareup.moshi.Json

data class ProductionCountry(
    @field:Json(name = "iso_3166_1")
    val iso31661: String,
    @field:Json(name = "name")
    val name: String
)
