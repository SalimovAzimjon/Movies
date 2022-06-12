package uz.direction.movie_detail.data.model


import com.squareup.moshi.Json

data class Logo(
    @field:Json(name = "aspect_ratio")
    val aspectRatio: Double,
    @field:Json(name = "path")
    val path: String
)
