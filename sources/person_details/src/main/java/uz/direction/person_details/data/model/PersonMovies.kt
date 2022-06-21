package uz.direction.person_details.data.model


import com.squareup.moshi.Json

data class PersonMovies(
    @field:Json(name = "cast")
    val movie: List<Movie>,
    @field:Json(name = "crew")
    val crew: List<Crew>,
    @field:Json(name = "id")
    val id: Int
)
