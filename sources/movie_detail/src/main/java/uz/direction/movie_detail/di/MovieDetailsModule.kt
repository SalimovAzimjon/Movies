package uz.direction.movie_detail.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import uz.direction.movie_detail.MovieDetailsViewModel
import uz.direction.movie_detail.data.datasource.MovieDetailsDataSource
import uz.direction.movie_detail.data.datasource.MovieDetailsDataSourceImpl
import uz.direction.movie_detail.data.network.MovieDetailsService
import uz.direction.movie_detail.mediator.MovieDetailsMediatorImpl
import uz.med.core_api.mediator.MovieDetailsMediator
import uz.med.shared.ViewModelKey
import uz.med.shared.ViewModelModuleContract

@Module
abstract class MovieDetailsModule : ViewModelModuleContract {

    @Module
    companion object {
        @Provides
        fun provideMovieService(retrofit: Retrofit): MovieDetailsService {
            return retrofit.create(MovieDetailsService::class.java)
        }
    }

    @Binds
    abstract fun bindMediator(movieDetailsMediatorImpl: MovieDetailsMediatorImpl): MovieDetailsMediator

    @Binds
    abstract fun bindDataSource(movieDetailsDataSourceImpl: MovieDetailsDataSourceImpl): MovieDetailsDataSource

    @ViewModelKey(MovieDetailsViewModel::class)
    @IntoMap
    @Binds
    abstract fun bindMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel
}
