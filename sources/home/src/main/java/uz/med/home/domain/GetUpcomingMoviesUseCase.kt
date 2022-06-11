package uz.med.home.domain

import uz.med.home.data.datasource.MovieDataSource
import uz.med.home.util.MoviesPagingFlow
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val moviesDataSource: MovieDataSource
) {

    fun execute(): MoviesPagingFlow =
        moviesDataSource.getUpcomingMovies()

}
