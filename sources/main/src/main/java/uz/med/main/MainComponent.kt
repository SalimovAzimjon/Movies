package uz.med.main

import dagger.Component
import uz.med.core_api.CoreDependenciesProvider

@Component(
    dependencies = [CoreDependenciesProvider::class]
)
interface MainComponent {

    companion object {
        fun create(providersProvider: CoreDependenciesProvider): MainComponent {
            return DaggerMainComponent.builder().coreDependenciesProvider(providersProvider).build()
        }
    }

    fun inject(mainActivity: MainActivity)
}
