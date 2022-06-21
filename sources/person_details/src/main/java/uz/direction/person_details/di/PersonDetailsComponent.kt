package uz.direction.person_details.di

import dagger.Component
import uz.direction.person_details.PersonDetailsFragment
import uz.med.core_api.CoreDependenciesProvider

@Component(
    dependencies = [CoreDependenciesProvider::class],
    modules = [PersonDetailsModule::class]
)
interface PersonDetailsComponent {

    companion object {
        fun create(coreDependenciesProvider: CoreDependenciesProvider): PersonDetailsComponent {
            return DaggerPersonDetailsComponent.builder()
                .coreDependenciesProvider(coreDependenciesProvider).build()
        }
    }

    fun inject(fragment: PersonDetailsFragment)
}
