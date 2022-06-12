package uz.direction.movie_detail.data.model


import com.squareup.moshi.Json

data class CreatedBy(
    @field:Json(name = "credit_id")
    val creditId: String,
    @field:Json(name = "gender")
    val gender: Int,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "profile_path")
    val profilePath: Any?
)
