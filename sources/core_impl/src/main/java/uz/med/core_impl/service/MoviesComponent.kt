package uz.med.core_impl.service

import dagger.Component
import uz.med.core_api.AppProvider
import uz.med.core_api.service.ProviderMoviesService
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [RetrofitModule::class]
)
interface MoviesComponent : ProviderMoviesService {

    companion object {
        fun create(): MoviesComponent {
            return DaggerMoviesComponent.builder().build()
        }
    }
}