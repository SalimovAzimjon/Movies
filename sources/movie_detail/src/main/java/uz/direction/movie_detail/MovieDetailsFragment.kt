package uz.direction.movie_detail

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.direction.movie_detail.data.model.MovieInformation
import uz.direction.movie_detail.data.model.TvInformation
import uz.direction.movie_detail.databinding.FragmentMovieDetailBinding
import uz.direction.movie_detail.di.MovieDetailsComponent
import uz.med.core_api.ApplicationFacade
import uz.med.core_api.dto.MediaType
import uz.med.shared.BaseFragment
import uz.med.shared.util.doOnSuccess
import uz.med.shared.util.setImageFromUrlPath
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailBinding>(
    R.layout.fragment_movie_detail,
    FragmentMovieDetailBinding::class.java
) {
    @Inject
    lateinit var factoryProvider: ViewModelProvider.Factory

    private val viewModel by viewModels<MovieDetailsViewModel> { factoryProvider }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MovieDetailsComponent.create((requireActivity().application as ApplicationFacade).getFacade())
            .inject(this)
    }

    override fun setUpUi() {
        viewModel.getMediaDetails(
            requireArguments().getLong(MOVIE_ID_KEY),
            requireArguments().getInt(MEDIA_TYPE_KEY)
        )
    }

    override suspend fun subscribeObservers() {
        coroutineScope {
            launch {
                viewModel.movieDetails.collect {
                    it.doOnSuccess { movieInformation ->
                        setMovieDetails(movieInformation)
                    }
                }
            }
            launch {
                viewModel.tvDetails.collect {
                    it.doOnSuccess { tvInformation ->
                        setTvDetails(tvInformation)
                    }
                }
            }
        }
    }

    private fun setTvDetails(tvInformation: TvInformation) = bind {
        val tvDetails = tvInformation.tvDetails
        val castAdapter = CastAdapter(tvInformation.credit.cast)
        rvCast.adapter = castAdapter
        imgMovie.setImageFromUrlPath(
            tvDetails.posterPath,
            ContextCompat.getDrawable(requireContext(), R.drawable.placeholder)
        )

        tvNextEpisode.isVisible = tvDetails.nextEpisodeToAir != null
        tvNextEpisode.text = "Next episode in ${tvDetails.nextEpisodeToAir?.airDate}"
        tvMovieRuntime.text =
            "${tvDetails.numberOfSeasons} seasons, ${tvDetails.numberOfEpisodes} episodes"
        ongoing.isVisible = tvDetails.inProduction
        tvMovieName.text = tvDetails.name
        tvMovieOverview.text = tvDetails.overview
        tvMovieRating.text = tvDetails.voteAverage.toString()
        tvMovieTagline.text = tvDetails.tagline
    }

    private fun setMovieDetails(movieInformation: MovieInformation) = bind {
        val movieDetails = movieInformation.movieDetails
        val castAdapter = CastAdapter(movieInformation.movieCredit.cast)
        rvCast.adapter = castAdapter
        imgMovie.setImageFromUrlPath(
            movieDetails.posterPath,
            ContextCompat.getDrawable(requireContext(), R.drawable.placeholder)
        )

        val hours = movieDetails.runtime / 60
        if (hours > 0)
            tvMovieRuntime.text = "${hours}h ${movieDetails.runtime % 60}m"
        else
            tvMovieRuntime.text = movieDetails.runtime.toString()

        tvMovieName.text = movieDetails.title
        tvMovieOverview.text = movieDetails.overview
        tvMovieRating.text = movieDetails.voteAverage.toString()
        tvMovieTagline.text = movieDetails.tagline
    }

    companion object {
        const val MOVIE_ID_KEY = "movie_id"
        const val MEDIA_TYPE_KEY = "media_type"

        fun newInstance(movieId: Long, mediaType: MediaType): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putLong(MOVIE_ID_KEY, movieId)
            bundle.putInt(MEDIA_TYPE_KEY, mediaType.code)
            movieDetailsFragment.arguments = bundle
            return movieDetailsFragment
        }
    }

}
