package uz.med.home.di

import dagger.Component
import uz.med.core.CoreProviderFactory
import uz.med.core_api.CoreDependenciesFacade
import uz.med.core_api.ViewModelsProvider
import uz.med.home.HomeFragment

@HomeScope
@Component(
    modules = [HomeModule::class],
    dependencies = [ViewModelsProvider::class, CoreDependenciesFacade::class]
)
interface HomeComponent : ViewModelsProvider {

    companion object {
        fun create(coreDependenciesFacade: CoreDependenciesFacade): HomeComponent {
            return DaggerHomeComponent.builder()
                .coreDependenciesFacade(coreDependenciesFacade)
                .viewModelsProvider(CoreProviderFactory.createViewModelBuilder()).build()
        }
    }

    fun inject(homeFragment: HomeFragment)
}
