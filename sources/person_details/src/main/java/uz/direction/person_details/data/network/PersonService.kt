package uz.direction.person_details.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import uz.direction.person_details.data.model.Person
import uz.direction.person_details.data.model.PersonMovies

interface PersonService {
    @GET("person/{person_id}")
    suspend fun getPersonDetails(@Path("person_id") personId: Long): Response<Person>

    @GET("person/{person_id}/movie_credits")
    suspend fun getPersonMovies(@Path("person_id") personId: Long): Response<PersonMovies>

}
