package uz.med.home.util

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.med.core_api.dto.Movie
import uz.med.shared.util.Resource

typealias ResourceMoviePagingData = Resource<PagingData<Movie>>
typealias MoviesPagingFlow = Flow<PagingData<Movie>>
