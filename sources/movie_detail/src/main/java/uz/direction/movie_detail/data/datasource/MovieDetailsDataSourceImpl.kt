package uz.direction.movie_detail.data.datasource

import kotlinx.coroutines.flow.Flow
import uz.direction.movie_detail.data.model.*
import uz.direction.movie_detail.data.network.MovieDetailsService
import uz.med.shared.util.Resource
import uz.med.shared.util.handleResponse
import uz.med.shared.util.safeCall
import javax.inject.Inject

class MovieDetailsDataSourceImpl @Inject constructor(
    private val movieDetailsService: MovieDetailsService
) : MovieDetailsDataSource {

    override suspend fun getMovieDetails(movieId: Long): MovieDetails {
        return movieDetailsService.getMovieDetails(movieId).handleResponse()
    }

    override suspend fun getTvDetails(tvId: Long): TvDetails {
        return movieDetailsService.getTvDetails(tvId).handleResponse()
    }

    override suspend fun getMovieCast(movieId: Long): MovieCredit {
        return movieDetailsService.getMovieCast(movieId).handleResponse()
    }

    override suspend fun getTvCast(tvId: Long): MovieCredit {
        return movieDetailsService.getTvCast(tvId).handleResponse()
    }

    override fun getMovieFullInformation(movieId: Long): Flow<Resource<MovieInformation>> =
        safeCall {
            val movieDetails = getMovieDetails(movieId)
            val movieCast = getMovieCast(movieId)
            MovieInformation(movieDetails, movieCast)
        }

    override fun getTvFullInformation(tvId: Long): Flow<Resource<TvInformation>> =
        safeCall {
            val tvDetails = getTvDetails(tvId)
            val cast = getTvCast(tvId)
            TvInformation(tvDetails, cast)
        }

}
