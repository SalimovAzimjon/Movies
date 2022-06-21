package uz.direction.person_details.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import uz.direction.person_details.PersonDetailsViewModel
import uz.direction.person_details.data.network.PersonService
import uz.direction.person_details.data.repository.PersonDataSourceImpl
import uz.direction.person_details.data.repository.PersonDatasource
import uz.direction.person_details.mediator.PersonDetailsMediatorImpl
import uz.med.core_api.mediator.PersonDetailsMediator
import uz.med.shared.ViewModelKey
import uz.med.shared.ViewModelModuleContract

@Module
interface PersonDetailsModule : ViewModelModuleContract {

    companion object {
        @Provides
        fun providePersonDetailsService(retrofit: Retrofit): PersonService {
            return retrofit.create(PersonService::class.java)
        }
    }

    @Binds
    fun bindPersonDetailsDataSource(personDataSourceImpl: PersonDataSourceImpl): PersonDatasource

    @ViewModelKey(PersonDetailsViewModel::class)
    @IntoMap
    @Binds
    fun bindPersonDetailsViewModel(personDetailsViewModel: PersonDetailsViewModel): ViewModel

}
