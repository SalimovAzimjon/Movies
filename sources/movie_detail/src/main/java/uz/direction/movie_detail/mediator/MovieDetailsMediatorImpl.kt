package uz.direction.movie_detail.mediator

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import uz.direction.movie_detail.MovieDetailsFragment
import uz.med.core_api.dto.MediaType
import uz.med.core_api.mediator.MovieDetailsMediator
import javax.inject.Inject

class MovieDetailsMediatorImpl @Inject constructor(
    private val router: Router
) : MovieDetailsMediator {

    override fun openMovieDetailsScreen(movieId: Long, mediaType: MediaType) {
        router.navigateTo(
            FragmentScreen.invoke(
                key = MovieDetailsFragment::class.java.simpleName,
                fragmentCreator = { MovieDetailsFragment.newInstance(movieId, mediaType) }
            ))
    }
}
