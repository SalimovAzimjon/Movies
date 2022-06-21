package uz.direction.person_details

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import uz.direction.person_details.data.model.PersonFullInformation
import uz.direction.person_details.databinding.FragmentPersonDetailsBinding
import uz.direction.person_details.di.PersonDetailsComponent
import uz.med.core_api.ApplicationFacade
import uz.med.shared.BaseFragment
import uz.med.shared.util.*
import javax.inject.Inject

class PersonDetailsFragment : BaseFragment<FragmentPersonDetailsBinding>(
    R.layout.fragment_person_details,
    FragmentPersonDetailsBinding::class.java
) {

    @Inject
    lateinit var factoryProvider: ViewModelProvider.Factory

    private val viewModel by viewModels<PersonDetailsViewModel> { factoryProvider }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PersonDetailsComponent.create((requireActivity().application as ApplicationFacade).getFacade())
            .inject(this)
    }

    override fun setUpUi() = bind {
        viewModel.getPersonDetails(requireArguments().getLong(PERSON_ID_KEY))
    }

    override suspend fun subscribeObservers() {
        coroutineScope {
            launch {
                viewModel.personDetails.collectResource {
                    doOnLoading { binding.progressBar.visible() }
                    doOnSuccess { personFullInformation -> setPersonData(personFullInformation) }
                    doOnError {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.base_error),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBar.invisible()
                    }
                }
            }
        }
    }

    private fun setPersonData(personFullInformation: PersonFullInformation) = bind {
        progressBar.invisible()
        imgPerson.setImageFromUrlPath(personFullInformation.person.profilePath)
        tvPersonBiography.text = personFullInformation.person.biography
        tvPersonName.text = personFullInformation.person.name
        tvPersonMovies.isVisible = personFullInformation.personMovies.movie.isNotEmpty()
        rvPersonMovies.adapter = PersonMoviesAdapter(personFullInformation.personMovies.movie) {
            viewModel.openToMovieDetailsScreen(it)
        }
    }

    companion object {
        const val PERSON_ID_KEY = "person_id"

        fun newInstance(personId: Long): PersonDetailsFragment {
            val fragment = PersonDetailsFragment()
            fragment.arguments = Bundle().also {
                it.putLong(PERSON_ID_KEY, personId)
            }
            return fragment
        }
    }

}
