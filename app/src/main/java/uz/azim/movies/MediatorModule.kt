package uz.azim.movies

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides
import uz.med.core_api.HomeMediator
import uz.med.home.di.HomeMediatorImpl
import javax.inject.Singleton

@Module
interface MediatorModule {

    companion object {
        @Singleton
        @Provides
        fun provideCicerone(): Cicerone<Router> {
            return Cicerone.create()
        }

        @Singleton
        @Provides
        fun provideRouter(cicerone: Cicerone<Router>): Router {
            return cicerone.router
        }

        @Singleton
        @Provides
        fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder {
            return cicerone.getNavigatorHolder()
        }
    }

    @Binds
    fun homeMediator(homeMediatorImpl: HomeMediatorImpl): HomeMediator

}