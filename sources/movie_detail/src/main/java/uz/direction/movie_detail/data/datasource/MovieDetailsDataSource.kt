package uz.direction.movie_detail.data.datasource

import kotlinx.coroutines.flow.Flow
import uz.direction.movie_detail.data.model.*
import uz.med.shared.util.Resource

interface MovieDetailsDataSource {

    suspend fun getMovieDetails(movieId: Long): MovieDetails

    suspend fun getTvDetails(tvId: Long): TvDetails

    suspend fun getMovieCast(movieId: Long): MovieCredit

    suspend fun getTvCast(tvId: Long): MovieCredit

    fun getMovieFullInformation(movieId: Long): Flow<Resource<MovieInformation>>

    fun getTvFullInformation(tvId: Long): Flow<Resource<TvInformation>>

}
