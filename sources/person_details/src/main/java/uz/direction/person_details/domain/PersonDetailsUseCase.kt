package uz.direction.person_details.domain

import uz.direction.person_details.data.repository.PersonDatasource
import uz.med.shared.util.safeCall
import javax.inject.Inject

class PersonDetailsUseCase @Inject constructor(private val personDatasource: PersonDatasource) {

    fun getPersonDetails(personId: Long) = safeCall {
        personDatasource.getPersonDetails(personId)
    }

}
