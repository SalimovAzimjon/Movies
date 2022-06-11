package uz.med.home.data.datasource

import uz.med.home.util.MoviesPagingFlow

interface MovieDataSource {
    fun getPopularMovies(): MoviesPagingFlow
    fun getTopRatedMovies(): MoviesPagingFlow
    fun getUpcomingMovies(): MoviesPagingFlow
    fun getTrendingMovies(): MoviesPagingFlow
    fun getTrendingTv(): MoviesPagingFlow
}
