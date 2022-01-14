package uz.azim.movies

import android.app.Application
import dagger.Component
import uz.med.core.CoreProviderFactory
import uz.med.core_api.AppProvider
import uz.med.core_api.CoreDependenciesFacade
import uz.med.core_api.service.ProviderMoviesService
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class, ProviderMoviesService::class]
)
interface FacadeComponent : CoreDependenciesFacade {

    companion object {
        fun create(application: Application): FacadeComponent {
            return DaggerFacadeComponent.builder()
                .providerMoviesService(CoreProviderFactory.provideMovieService())
                .appProvider(AppComponent.create(application))
                .build()
        }
    }

    fun inject(application: Application)
}