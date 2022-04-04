package uz.med.core_api

import com.github.terrakok.cicerone.NavigatorHolder

interface MediatorProvider {

    fun provideHomeMediator(): HomeMediator

    fun provideNavigationHolder(): NavigatorHolder

}
