package uz.direction.movie_detail.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.direction.movie_detail.data.model.MovieCredit
import uz.direction.movie_detail.data.model.MovieDetails
import uz.direction.movie_detail.data.model.TvDetails

interface MovieDetailsService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Long): Response<MovieDetails>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(@Path("movie_id") movieId: Long): Response<MovieCredit>

    @GET("tv/{tv_id}")
    suspend fun getTvDetails(@Path("tv_id") tvId: Long): Response<TvDetails>

    @GET("tv/{tv_id}/credits")
    suspend fun getTvCast(@Path("tv_id") tvId: Long): Response<MovieCredit>

}
