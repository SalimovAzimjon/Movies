package uz.med.home.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import uz.med.home.domain.*
import uz.med.home.util.ResourceMoviePagingData
import uz.med.shared.BaseViewModel
import uz.med.shared.util.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val getTrendingTvUseCase: GetTrendingTvUseCase,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel(ioDispatcher) {

    private val _popularMovies = MutableStateFlow<ResourceMoviePagingData>(Resource.Idle())
    private val _upcomingMovies = MutableStateFlow<ResourceMoviePagingData>(Resource.Idle())
    private val _topRatedMovies = MutableStateFlow<ResourceMoviePagingData>(Resource.Idle())
    private val _trendingMovies = MutableStateFlow<ResourceMoviePagingData>(Resource.Idle())
    private val _trendingTv = MutableStateFlow<ResourceMoviePagingData>(Resource.Idle())

    val popularMovies: StateFlow<ResourceMoviePagingData> = _popularMovies
    val upcomingMovies: StateFlow<ResourceMoviePagingData> = _upcomingMovies
    val topRatedMovies: StateFlow<ResourceMoviePagingData> = _topRatedMovies
    val trendingMovies: StateFlow<ResourceMoviePagingData> = _trendingMovies
    val trendingTv: StateFlow<ResourceMoviePagingData> = _trendingTv

    init {
        getUpcomingMovies()
        getPopularMovies()
        getTopRatedMovies()
        getTrendingMovies()
        getTrendingTv()
    }

    fun getUpcomingMovies() = launchInViewModelScope {
        getUpcomingMoviesUseCase.execute()
            .cachedIn(viewModelScope)
            .onStart {
                _upcomingMovies.emit(Resource.Loading())
            }
            .collect {
                _upcomingMovies.emit(Resource.Success(it))
            }
    }

    fun getPopularMovies() = launchInViewModelScope {
        getPopularMoviesUseCase.execute()
            .cachedIn(viewModelScope)
            .onStart {
                _popularMovies.emit(Resource.Loading())
            }
            .collect {
                _popularMovies.emit(Resource.Success(it))
            }
    }

    fun getTopRatedMovies() = launchInViewModelScope {
        getTopRatedMoviesUseCase.execute()
            .cachedIn(viewModelScope)
            .onStart {
                _topRatedMovies.emit(Resource.Loading())
            }
            .collect {
                _topRatedMovies.emit(Resource.Success(it))
            }
    }

    fun getTrendingMovies() = launchInViewModelScope {
        getTrendingMoviesUseCase.execute()
            .cachedIn(viewModelScope)
            .onStart {
                _trendingMovies.emit(Resource.Loading())
            }
            .collect {
                _trendingMovies.emit(Resource.Success(it))
            }
    }

    fun getTrendingTv() = launchInViewModelScope {
        getTrendingTvUseCase.execute()
            .cachedIn(viewModelScope)
            .onStart {
                _trendingTv.emit(Resource.Loading())
            }
            .collect {
                _trendingTv.emit(Resource.Success(it))
            }
    }

}
