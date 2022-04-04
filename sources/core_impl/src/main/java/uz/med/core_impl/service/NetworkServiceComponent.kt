package uz.med.core_impl.service

import dagger.Component
import uz.med.core_api.AppProvider
import uz.med.core_api.service.NetworkServiceProvider
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class]
)
interface NetworkServiceComponent : NetworkServiceProvider {

    companion object {
        fun create(): NetworkServiceComponent {
            return DaggerNetworkServiceComponent.builder()
                .build()
        }
    }
}
