package uz.direction.movie_detail

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import uz.direction.movie_detail.data.model.MovieInformation
import uz.direction.movie_detail.data.model.TvInformation
import uz.direction.movie_detail.domain.MovieDetailsUseCase
import uz.direction.movie_detail.domain.TvDetailsUseCase
import uz.med.core_api.dto.MediaType
import uz.med.shared.BaseViewModel
import uz.med.shared.util.Resource
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase,
    private val tvDetailsUseCase: TvDetailsUseCase,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel(ioDispatcher) {

    private val _movieDetails = MutableStateFlow<Resource<MovieInformation>>(Resource.Idle())
    private val _tvDetails = MutableStateFlow<Resource<TvInformation>>(Resource.Idle())

    val movieDetails = _movieDetails.asStateFlow()
    val tvDetails = _tvDetails.asStateFlow()

    private fun getMovieDetails(movieId: Long) = launchInIOScope {
        movieDetailsUseCase.execute(movieId).collect {
            _movieDetails.emit(it)
        }
    }

    private fun getTvDetails(tvId: Long) = launchInIOScope {
        tvDetailsUseCase.execute(tvId).collect {
            _tvDetails.emit(it)
        }
    }

    fun getMediaDetails(mediaId: Long, mediaType: Int) {
        if (mediaType == MediaType.TRENDING_TV.code) {
            getTvDetails(mediaId)
        } else {
            getMovieDetails(mediaId)
        }
    }

}
