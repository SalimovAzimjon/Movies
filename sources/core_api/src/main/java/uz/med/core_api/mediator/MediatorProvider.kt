package uz.med.core_api.mediator

import com.github.terrakok.cicerone.NavigatorHolder

interface MediatorProvider {

    fun provideHomeMediator(): HomeMediator

    fun provideMovieDetailsMediator(): MovieDetailsMediator

    fun providePersonDetailsMediator(): PersonDetailsMediator

    fun provideNavigationHolder(): NavigatorHolder

}
