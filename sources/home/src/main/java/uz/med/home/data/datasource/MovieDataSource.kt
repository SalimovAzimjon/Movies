package uz.med.home.data.datasource

import kotlinx.coroutines.flow.Flow
import uz.med.core_api.dto.Movie
import uz.med.core_api.dto.TrendingMovies
import uz.med.home.util.ResourceMovieList
import uz.med.shared.util.Resource

interface MovieDataSource {

    fun getPopularMovies(page: Int): Flow<ResourceMovieList>
    fun getTopRatedMovies(page: Int): Flow<ResourceMovieList>
    fun getUpcomingMovies(page: Int): Flow<ResourceMovieList>
    fun getTrendingMovies(page: Int): Flow<Resource<List<TrendingMovies>>>
    fun getTrendingTv(page: Int): Flow<Resource<List<TrendingMovies>>>

}
