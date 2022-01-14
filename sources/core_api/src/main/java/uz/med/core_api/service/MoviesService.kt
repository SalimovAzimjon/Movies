package uz.med.core_api.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.med.core_api.dto.BaseResponse
import uz.med.core_api.dto.Movie
import uz.med.core_api.dto.TrendingMovies

interface MoviesService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<BaseResponse<Movie>>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): Response<BaseResponse<Movie>>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): Response<BaseResponse<Movie>>

    @GET("/trending/movie/day")
    suspend fun getTrendingMovies(@Query("page") page: Int): Response<BaseResponse<TrendingMovies>>

    @GET("/trending/tv/day")
    suspend fun getTrendingTv(@Query("page") page: Int): Response<BaseResponse<TrendingMovies>>

}
