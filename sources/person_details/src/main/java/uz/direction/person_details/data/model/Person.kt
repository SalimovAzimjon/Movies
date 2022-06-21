package uz.direction.person_details.data.model


import com.squareup.moshi.Json

data class Person(
    @field:Json(name = "adult")
    val adult: Boolean,
    @field:Json(name = "also_known_as")
    val alsoKnownAs: List<String>,
    @field:Json(name = "biography")
    val biography: String,
    @field:Json(name = "birthday")
    val birthday: String,
    @field:Json(name = "deathday")
    val deathday: Any?,
    @field:Json(name = "gender")
    val gender: Int,
    @field:Json(name = "homepage")
    val homepage: Any?,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "imdb_id")
    val imdbId: String,
    @field:Json(name = "known_for_department")
    val knownForDepartment: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "place_of_birth")
    val placeOfBirth: String,
    @field:Json(name = "popularity")
    val popularity: Double,
    @field:Json(name = "profile_path")
    val profilePath: String
)
