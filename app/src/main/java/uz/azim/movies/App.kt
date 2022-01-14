package uz.azim.movies

import android.app.Application
import uz.med.core_api.ApplicationFacade
import uz.med.core_api.CoreDependenciesFacade

class App : Application(), ApplicationFacade {

    companion object {

        private var facadeComponent: FacadeComponent? = null
    }

    override fun getFacade(): CoreDependenciesFacade {
        return facadeComponent ?: FacadeComponent.create(this).also {
            facadeComponent = it
        }
    }

    override fun onCreate() {
        super.onCreate()
        (getFacade() as FacadeComponent).inject(this)
    }

}