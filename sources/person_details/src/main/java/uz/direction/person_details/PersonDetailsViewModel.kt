package uz.direction.person_details

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import uz.direction.person_details.data.model.PersonFullInformation
import uz.direction.person_details.domain.PersonDetailsUseCase
import uz.med.core_api.dto.MediaType
import uz.med.core_api.mediator.MovieDetailsMediator
import uz.med.shared.BaseViewModel
import uz.med.shared.util.Resource
import javax.inject.Inject

class PersonDetailsViewModel @Inject constructor(
    private val personDetailsUseCase: PersonDetailsUseCase,
    private val movieDetailsMediator: MovieDetailsMediator,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel(ioDispatcher) {

    private val _personDetails = MutableStateFlow<Resource<PersonFullInformation>>(Resource.Idle())
    val personDetails get() = _personDetails.asStateFlow()

    fun getPersonDetails(personId: Long) = launchInIOScope {
        personDetailsUseCase.getPersonDetails(personId)
            .collect {
                _personDetails.emit(it)
            }
    }

    fun openToMovieDetailsScreen(movieId: Long) = launchInMainScope {
        movieDetailsMediator.openMovieDetailsScreen(movieId, MediaType.DEFAULT_MOVIE)
    }

}
