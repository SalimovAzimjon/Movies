package uz.direction.movie_detail.domain

import uz.direction.movie_detail.data.datasource.MovieDetailsDataSource
import javax.inject.Inject

class TvDetailsUseCase @Inject constructor(
    private val moviesDataSource: MovieDetailsDataSource
) {
    fun execute(tvId: Long) = moviesDataSource.getTvFullInformation(tvId)
}
