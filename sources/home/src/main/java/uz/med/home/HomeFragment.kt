package uz.med.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber
import uz.med.core_api.ApplicationFacade
import uz.med.home.databinding.FragmentHomeBinding
import uz.med.home.di.HomeComponent
import uz.med.home.util.ResourceMovieList
import uz.med.home.viewmodel.HomeViewModel
import uz.med.shared.BaseFragment
import uz.med.shared.util.collectResource
import uz.med.shared.util.doOnError
import uz.med.shared.util.doOnSuccess
import javax.inject.Inject

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(R.layout.fragment_home, FragmentHomeBinding::class.java) {

    @Inject
    lateinit var factoryProvider: ViewModelProvider.Factory

    private val viewModel by viewModels<HomeViewModel> { factoryProvider }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeComponent.create((requireActivity().application as ApplicationFacade).getFacade())
            .inject(this)
    }

    override fun setUpUi() {
        viewModel.getPopularMovies(1)
    }

    override suspend fun subscribeObservers() {
        viewModel.popularMovies.collectResource(this::setPopularMovies)
    }

    private fun setPopularMovies(moviesResource: ResourceMovieList) {
        moviesResource
            .doOnSuccess {movies->
                Timber.i(movies.toString())
            }
            .doOnError {
                Timber.e(it.toString())
            }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

}
