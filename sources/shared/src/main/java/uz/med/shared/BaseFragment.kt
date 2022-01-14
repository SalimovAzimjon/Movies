package uz.med.shared

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewBinding>(
    @LayoutRes resId: Int,
    bindingClass: Class<T>
) : Fragment(resId) {


    private val binding: T by viewBinding(bindingClass)

    fun <R> bind(block: T.() -> R) {
        block(binding)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            subscribeObservers()
        }
    }

    /**
     * This method used only for observing data from flows
     */
    abstract suspend fun subscribeObservers()

}