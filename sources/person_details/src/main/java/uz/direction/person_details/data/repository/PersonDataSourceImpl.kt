package uz.direction.person_details.data.repository

import uz.direction.person_details.data.model.PersonFullInformation
import uz.direction.person_details.data.network.PersonService
import uz.med.shared.util.handleResponse
import javax.inject.Inject

class PersonDataSourceImpl @Inject constructor(
    private val personService: PersonService
) : PersonDatasource {

    override suspend fun getPersonDetails(personId: Long): PersonFullInformation {
        val person = personService.getPersonDetails(personId)
        val personCast = personService.getPersonMovies(personId)
        return PersonFullInformation(person.handleResponse(), personCast.handleResponse())
    }
}
