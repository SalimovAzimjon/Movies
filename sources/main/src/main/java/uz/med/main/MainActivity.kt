package uz.med.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import uz.med.core_api.ApplicationFacade
import uz.med.core_api.HomeMediator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val navigator by lazy(LazyThreadSafetyMode.NONE) { AppNavigator(this, R.id.container) }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @Inject
    lateinit var homeMediator: HomeMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as ApplicationFacade).getFacade()).inject(this)
        setContentView(R.layout.activity_main)
        homeMediator.openMovieScreen()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}
