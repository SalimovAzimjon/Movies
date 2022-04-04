package uz.med.core

import uz.med.core_api.AppProvider
import uz.med.core_api.service.NetworkServiceProvider
import uz.med.core_impl.service.NetworkServiceComponent

object CoreProviderFactory {

    fun createNetworkService(appProvider: AppProvider): NetworkServiceProvider {
        return NetworkServiceComponent.create()
    }

}
