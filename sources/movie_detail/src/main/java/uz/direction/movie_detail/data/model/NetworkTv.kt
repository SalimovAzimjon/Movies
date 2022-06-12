package uz.direction.movie_detail.data.model


import com.squareup.moshi.Json

data class NetworkTv(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "logo")
    val logo: Logo,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "origin_country")
    val originCountry: String
)
