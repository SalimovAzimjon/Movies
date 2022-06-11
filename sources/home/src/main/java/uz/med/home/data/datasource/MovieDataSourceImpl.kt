package uz.med.home.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import uz.med.home.data.network.MoviesService
import uz.med.home.util.MoviesPagingFlow
import uz.med.shared.util.handleResponse
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val moviesService: MoviesService
) : MovieDataSource {

    private val defaultPageSize = 25

    override fun getPopularMovies(): MoviesPagingFlow {
        return Pager(
            config = PagingConfig(pageSize = defaultPageSize, enablePlaceholders = false)
        ) {
            MoviesPagingSource { page ->
                moviesService.getPopularMovies(page).handleResponse()
            }
        }.flow
    }

    override fun getTopRatedMovies(): MoviesPagingFlow {
        return Pager(
            config = PagingConfig(pageSize = defaultPageSize, enablePlaceholders = false)
        ) {
            MoviesPagingSource { page ->
                moviesService.getTopRatedMovies(page).handleResponse()
            }
        }.flow
    }

    override fun getUpcomingMovies(): MoviesPagingFlow {
        return Pager(
            config = PagingConfig(pageSize = defaultPageSize, enablePlaceholders = false)
        ) {
            MoviesPagingSource { page ->
                moviesService.getUpcomingMovies(page).handleResponse()
            }
        }.flow
    }

    override fun getTrendingMovies(): MoviesPagingFlow {
        return Pager(
            config = PagingConfig(pageSize = defaultPageSize, enablePlaceholders = false)
        ) {
            MoviesPagingSource { page ->
                moviesService.getTrendingMovies(page).handleResponse()
            }
        }.flow
    }

    override fun getTrendingTv(): MoviesPagingFlow {
        return Pager(
            config = PagingConfig(pageSize = defaultPageSize, enablePlaceholders = false)
        ) {
            MoviesPagingSource { page ->
                moviesService.getTrendingTv(page).handleResponse()
            }
        }.flow
    }

}
