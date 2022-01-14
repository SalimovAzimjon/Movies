package uz.med.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import uz.med.core_api.CoreDependenciesFacade
import uz.med.home.databinding.FragmentHomeBinding
import uz.med.home.di.HomeComponent
import uz.med.home.viewmodel.HomeViewModel
import uz.med.shared.BaseFragment

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(R.layout.fragment_home, FragmentHomeBinding::class.java) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeComponent.create((requireActivity().application as CoreDependenciesFacade)).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override suspend fun subscribeObservers() {}
}
