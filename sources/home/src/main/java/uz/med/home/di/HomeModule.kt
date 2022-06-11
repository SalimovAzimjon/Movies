package uz.med.home.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import uz.med.home.data.datasource.MovieDataSource
import uz.med.home.data.datasource.MovieDataSourceImpl
import uz.med.home.data.network.MoviesService
import uz.med.home.viewmodel.HomeViewModel
import uz.med.shared.ViewModelKey
import uz.med.shared.ViewModelModuleContract

@Module
abstract class HomeModule : ViewModelModuleContract {

    @Module
    companion object {
        @Provides
        @HomeScope
        fun provideMovieService(retrofit: Retrofit): MoviesService {
            return retrofit.create(MoviesService::class.java)
        }
    }

    @HomeScope
    @ViewModelKey(HomeViewModel::class)
    @IntoMap
    @Binds
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @HomeScope
    @Binds
    abstract fun bindMoviesDataSource(movieDataSourceImpl: MovieDataSourceImpl): MovieDataSource
}
