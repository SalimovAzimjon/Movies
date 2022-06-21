package uz.direction.person_details.data.repository

import uz.direction.person_details.data.model.PersonFullInformation

interface PersonDatasource {

    suspend fun getPersonDetails(personId: Long): PersonFullInformation

}
