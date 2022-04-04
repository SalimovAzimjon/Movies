package uz.med.home.di

import dagger.Component
import uz.med.core_api.CoreDependenciesProvider
import uz.med.home.HomeFragment

@HomeScope
@Component(
    dependencies = [CoreDependenciesProvider::class],
    modules = [HomeModule::class],
)
interface HomeComponent {

    companion object {
        fun create(coreDependenciesProvider: CoreDependenciesProvider): HomeComponent {
            return DaggerHomeComponent.builder()
                .coreDependenciesProvider(coreDependenciesProvider)
                .build()
        }
    }

    fun inject(homeFragment: HomeFragment)
}
