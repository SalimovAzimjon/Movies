package uz.med.shared.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.*
import uz.med.shared.BaseException

suspend fun <T> StateFlow<Resource<T>>.safeCollectResource(
    lifecycleOwner: LifecycleOwner,
    block: Resource<T>.() -> Unit
) {
    lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        collect {
            block.invoke(it)
        }
    }
}

suspend fun <T> SharedFlow<Resource<T>>.collectResource(
    lifecycleOwner: LifecycleOwner,
    block: Resource<T>.() -> Unit
) {
    lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        collect {
            block.invoke(it)
        }
    }
}

fun <T> safeCall(call: suspend () -> T): Flow<Resource<T>> = flow {
    try {
        emit(Resource.Loading())
        emit(Resource.Success(call.invoke()))
    } catch (exception: BaseException) {
        emit(Resource.Error(exception))
    }
}
