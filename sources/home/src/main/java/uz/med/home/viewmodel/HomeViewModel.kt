package uz.med.home.viewmodel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import uz.med.home.data.usecase.GetPopularMoviesUseCase
import uz.med.shared.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel(ioDispatcher) {


    fun getPopularMovies(page: Int) = launch {
        getPopularMoviesUseCase.execute(page).collect {

        }
    }

}
