package uz.med.home.viewmodel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import uz.med.home.domain.usecase.GetPopularMoviesUseCase
import uz.med.home.util.ResourceMovieList
import uz.med.shared.BaseViewModel
import uz.med.shared.util.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel(ioDispatcher) {

    private val _popularMovies = MutableStateFlow<ResourceMovieList>(Resource.Idle())
    val popularMovies: StateFlow<ResourceMovieList> = _popularMovies

    fun getPopularMovies(page: Int) = launch {
        getPopularMoviesUseCase.execute(page)
            .collect {
                _popularMovies.emit(it)
            }
    }

}
