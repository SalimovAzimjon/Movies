package uz.azim.movies

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uz.med.core_api.AppProvider
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent : AppProvider {

    companion object {
        fun create(application: Application): AppComponent {
            return DaggerAppComponent.builder().application(application.applicationContext).build()
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }
}
