package uz.med.core_api

import uz.med.core_api.mediator.MediatorProvider
import uz.med.core_api.service.NetworkServiceProvider

interface CoreDependenciesProvider : AppProvider, MediatorProvider, NetworkServiceProvider
