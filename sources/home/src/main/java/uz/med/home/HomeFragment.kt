package uz.med.home

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import uz.med.core_api.ApplicationFacade
import uz.med.core_api.dto.MediaType
import uz.med.core_api.dto.Movie
import uz.med.home.adapter.MoviesAdapter
import uz.med.home.databinding.FragmentHomeBinding
import uz.med.home.di.HomeComponent
import uz.med.home.util.ResourceMoviePagingData
import uz.med.home.viewmodel.HomeViewModel
import uz.med.shared.BaseFragment
import uz.med.shared.util.doOnError
import uz.med.shared.util.doOnLoading
import uz.med.shared.util.suspendOnSuccess
import uz.med.shared.util.unsafeLazy
import javax.inject.Inject

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(R.layout.fragment_home, FragmentHomeBinding::class.java) {

    @Inject
    lateinit var factoryProvider: ViewModelProvider.Factory

    private val viewModel by viewModels<HomeViewModel> { factoryProvider }
    private val upComingMoviesAdapter by unsafeLazy {
        MoviesAdapter(
            MediaType.UPCOMING,
            this::onMovieClicked
        )
    }
    private val popularMoviesAdapter by unsafeLazy {
        MoviesAdapter(
            MediaType.POPULAR,
            this::onMovieClicked
        )
    }
    private val topRatedMoviesAdapter by unsafeLazy {
        MoviesAdapter(
            MediaType.TOP_RATED,
            this::onMovieClicked
        )
    }
    private val trendingMoviesAdapter by unsafeLazy {
        MoviesAdapter(
            MediaType.TRENDING_MOVIES,
            this::onMovieClicked
        )
    }
    private val trendingTvAdapter by unsafeLazy {
        MoviesAdapter(
            MediaType.TRENDING_TV,
            this::onMovieClicked
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeComponent.create((requireActivity().application as ApplicationFacade).getFacade())
            .inject(this)
    }

    override fun setUpUi() = bind {
        rvUpcomingMovies.adapter = upComingMoviesAdapter
        rvPopularMovies.adapter = popularMoviesAdapter
        rvTopRatedMovies.adapter = topRatedMoviesAdapter
        rvTrendingMovies.adapter = trendingMoviesAdapter
        rvTrendingTv.adapter = trendingTvAdapter

    }

    override suspend fun subscribeObservers() {
        coroutineScope {
            launch {
                viewModel.upcomingMovies.collect {
                    setMoviesToAdapter(it) { movie ->
                        upComingMoviesAdapter.submitData(movie)
                    }
                }
            }
            launch {
                viewModel.popularMovies.collect {
                    setMoviesToAdapter(it) { movie ->
                        popularMoviesAdapter.submitData(movie)
                    }
                }
            }
            launch {
                viewModel.topRatedMovies.collect {
                    setMoviesToAdapter(it) { movie ->
                        topRatedMoviesAdapter.submitData(movie)
                    }
                }
            }
            launch {
                viewModel.trendingMovies.collect {
                    setMoviesToAdapter(it) { movie ->
                        trendingMoviesAdapter.submitData(movie)
                    }
                }
            }
            launch {
                viewModel.trendingTv.collect {
                    setMoviesToAdapter(it) { movie ->
                        trendingTvAdapter.submitData(movie)
                    }
                }
            }
        }
    }

    private fun onMovieClicked(movieId: Long, mediaType: MediaType) {
        viewModel.openMovieDetails(movieId, mediaType)
    }

    private suspend fun setMoviesToAdapter(
        moviesResource: ResourceMoviePagingData,
        function: suspend (PagingData<Movie>) -> Unit
    ) {
        moviesResource
            .doOnLoading  {
                binding.progressBar.show()
            }
            .suspendOnSuccess { movies ->
                binding.progressBar.hide()
                function.invoke(movies)
            }
            .doOnError {
                binding.progressBar.hide()
                Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
                Timber.e(it.toString())
            }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

}
