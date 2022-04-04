package uz.med.home.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.med.core_api.dto.Movie
import uz.med.home.data.datasource.MovieDataSource
import uz.med.shared.BaseUseCase
import uz.med.shared.util.Resource
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val moviesDataSource: MovieDataSource
) : BaseUseCase<Int, List<Movie>> {

    override fun execute(page: Int): Flow<Resource<List<Movie>>> =
        moviesDataSource.getPopularMovies(page)

}
