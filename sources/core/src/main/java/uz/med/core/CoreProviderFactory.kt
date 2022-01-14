package uz.med.core

import uz.med.core_api.ViewModelsProvider
import uz.med.core_api.service.ProviderMoviesService
import uz.med.core_impl.service.MoviesComponent
import uz.med.core_impl.viewmodel.DaggerViewModelComponent

object CoreProviderFactory {

    fun provideMovieService(): ProviderMoviesService {
        return MoviesComponent.create()
    }

    fun createViewModelBuilder(): ViewModelsProvider {
        return DaggerViewModelComponent.create()
    }

}