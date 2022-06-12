package uz.med.core_api.mediator

import uz.med.core_api.dto.MediaType

interface MovieDetailsMediator {

    fun openMovieDetailsScreen(movieId: Long, mediaType: MediaType)

}
