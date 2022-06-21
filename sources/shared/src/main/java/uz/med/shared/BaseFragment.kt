package uz.med.shared

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewBinding>(
    @LayoutRes resId: Int,
    bindingClass: Class<T>
) : Fragment(resId) {

    protected val binding: T by viewBinding(bindingClass)

    fun <R> bind(block: T.() -> R) {
        block(binding)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                setUpUi()
                subscribeObservers()
            }
        }
    }

    abstract fun setUpUi()

    /**
     * Called only once when fragment created.
     * Will not be called on re-create events
     * Good place for one time requests
     */
//    open fun initialSetUp() {}

    /**
     * This method used only for observing data from flows
     */
    abstract suspend fun subscribeObservers()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }
}
