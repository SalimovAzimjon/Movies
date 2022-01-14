package uz.med.home.data.datasource

import kotlinx.coroutines.flow.Flow
import uz.med.core_api.dto.Movie
import uz.med.core_api.dto.TrendingMovies
import uz.med.core_api.service.MoviesService
import uz.med.shared.util.Resource
import uz.med.shared.util.handleResponse
import uz.med.shared.util.safeCall
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val moviesService: MoviesService
) : MovieDataSource {
    override fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>> = safeCall {
        moviesService.getPopularMovies(page).handleResponse().results
    }

    override fun getTopRatedMovies(page: Int): Flow<Resource<List<Movie>>> = safeCall {
        moviesService.getTopRatedMovies(page).handleResponse().results
    }

    override fun getUpcomingMovies(page: Int): Flow<Resource<List<Movie>>> = safeCall {
        moviesService.getUpcomingMovies(page).handleResponse().results
    }

    override fun getTrendingMovies(page: Int): Flow<Resource<List<TrendingMovies>>> = safeCall {
        moviesService.getTrendingMovies(page).handleResponse().results
    }

    override fun getTrendingTv(page: Int): Flow<Resource<List<TrendingMovies>>> = safeCall {
        moviesService.getTrendingTv(page).handleResponse().results
    }

}
