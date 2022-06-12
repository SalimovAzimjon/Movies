package uz.direction.movie_detail.data.model


import com.squareup.moshi.Json

data class NextEpisodeToAir(
    @field:Json(name = "air_date")
    val airDate: String,
    @field:Json(name = "episode_number")
    val episodeNumber: Int,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "production_code")
    val productionCode: String,
    @field:Json(name = "runtime")
    val runtime: Any?,
    @field:Json(name = "season_number")
    val seasonNumber: Int,
    @field:Json(name = "still_path")
    val stillPath: Any?,
    @field:Json(name = "vote_average")
    val voteAverage: Double,
    @field:Json(name = "vote_count")
    val voteCount: Int
)
