package uz.direction.person_details.mediator

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import uz.direction.person_details.PersonDetailsFragment
import uz.med.core_api.mediator.PersonDetailsMediator
import javax.inject.Inject

class PersonDetailsMediatorImpl @Inject constructor(
    private val router: Router
) : PersonDetailsMediator {

    override fun openPersonDetailsScreen(personId: Long) {
        router.navigateTo(
            FragmentScreen.invoke(key = PersonDetailsFragment::class.simpleName,
                fragmentCreator = {
                    PersonDetailsFragment.newInstance(personId)
                })
        )
    }

}
