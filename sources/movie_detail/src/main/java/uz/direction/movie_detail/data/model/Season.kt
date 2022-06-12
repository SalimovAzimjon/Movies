package uz.direction.movie_detail.data.model


import com.squareup.moshi.Json

data class Season(
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "episode_count")
    val episodeCount: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "networks")
    val networks: List<NetworkTv>,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "season_number")
    val seasonNumber: Int
)
