package uz.direction.movie_detail.domain

import uz.direction.movie_detail.data.datasource.MovieDetailsDataSource
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val moviesDataSource: MovieDetailsDataSource
) {
    fun execute(movieId: Long) = moviesDataSource.getMovieFullInformation(movieId)
}
