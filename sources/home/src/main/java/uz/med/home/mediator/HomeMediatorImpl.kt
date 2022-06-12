package uz.med.home.mediator

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import uz.med.core_api.mediator.HomeMediator
import uz.med.home.HomeFragment
import javax.inject.Inject

class HomeMediatorImpl @Inject constructor(
    private val router: Router
) : HomeMediator {

    override fun openMovieScreen() {
        router.replaceScreen(
            FragmentScreen.invoke(
                key = HomeFragment::class.java.simpleName,
                fragmentCreator = { HomeFragment.newInstance() }
            ))
    }

}
