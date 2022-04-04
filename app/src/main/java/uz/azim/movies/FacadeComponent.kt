package uz.azim.movies

import android.app.Application
import dagger.Component
import uz.med.core.CoreProviderFactory
import uz.med.core_api.AppProvider
import uz.med.core_api.CoreDependenciesProvider
import uz.med.core_api.service.NetworkServiceProvider
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class, NetworkServiceProvider::class],
    modules = [MediatorModule::class]
)
interface FacadeComponent : CoreDependenciesProvider {

    companion object {
        fun create(application: Application): FacadeComponent {
            val appProvider = AppComponent.create(application)
            return DaggerFacadeComponent.builder()
                .appProvider(appProvider)
                .networkServiceProvider(CoreProviderFactory.createNetworkService(appProvider))
                .build()
        }
    }

    fun inject(application: Application)

}
