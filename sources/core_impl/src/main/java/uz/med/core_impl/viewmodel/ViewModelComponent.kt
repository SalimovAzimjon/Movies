package uz.med.core_impl.viewmodel

import dagger.Component
import uz.med.core_api.ViewModelsProvider
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class]
)
interface ViewModelComponent : ViewModelsProvider