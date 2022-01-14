package uz.med.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import uz.med.shared.util.Resource

abstract class BaseViewModel(val dispatcher: CoroutineDispatcher) : ViewModel() {

    private val _exception = MutableSharedFlow<Resource<Any>>()
    val exception = _exception.asSharedFlow()

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch {
            _exception.emit(Resource.Error(throwable))
        }
    }

    inline fun launch(crossinline block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(exceptionHandler + dispatcher) {
            block.invoke(this)
        }

}
