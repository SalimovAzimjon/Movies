package uz.med.home.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import uz.med.home.data.datasource.MovieDataSource
import uz.med.home.data.datasource.MovieDataSourceImpl
import uz.med.home.data.usecase.GetPopularMoviesUseCase
import uz.med.home.viewmodel.HomeViewModel

@Module
abstract class HomeModule {

    @Module
    companion object {
        @Provides
        @HomeScope
        @JvmStatic
        fun provideHomeViewModel(
            map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>,
            getPopularMoviesUseCase: GetPopularMoviesUseCase,
            ioDispatcher: CoroutineDispatcher
        ): ViewModel = HomeViewModel(getPopularMoviesUseCase,ioDispatcher).also {
            map[HomeViewModel::class.java] = it
        }
    }

    @Binds
    abstract fun bindMoviesDataSource(movieDataSourceImpl: MovieDataSourceImpl): MovieDataSource
}
