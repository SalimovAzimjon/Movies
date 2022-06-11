package uz.med.shared

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.viewBinding

abstract class BaseViewHolder<T : ViewBinding>(
    view: View,
    bindingClass: Class<T>
) : RecyclerView.ViewHolder(view) {

    private val binding: T by viewBinding(bindingClass)

    fun <R> bindView(block: T.() -> R) {
        block(binding)
    }

}
