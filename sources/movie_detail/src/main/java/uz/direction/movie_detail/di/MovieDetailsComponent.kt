package uz.direction.movie_detail.di

import dagger.Component
import uz.direction.movie_detail.MovieDetailsFragment
import uz.med.core_api.CoreDependenciesProvider

@Component(
    dependencies = [CoreDependenciesProvider::class],
    modules = [MovieDetailsModule::class]
)
interface MovieDetailsComponent {

    companion object {
        fun create(coreDependenciesProvider: CoreDependenciesProvider): MovieDetailsComponent {
            return DaggerMovieDetailsComponent.builder()
                .coreDependenciesProvider(coreDependenciesProvider)
                .build()
        }
    }

    fun inject(movieDetailsFragment: MovieDetailsFragment)

}
