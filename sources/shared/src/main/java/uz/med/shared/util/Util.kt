package uz.med.shared.util

import retrofit2.Response
import timber.log.Timber
import uz.med.shared.BaseException
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

fun <T> Response<T>.handleResponse(): T {
    val body = body()
    Timber.i(body.toString())
    return if (isSuccessful && body != null) {
        body
    } else {
        throw when (code()) {
            HTTP_INTERNAL_ERROR -> BaseException.ServerError(message())
            HTTP_UNAUTHORIZED -> BaseException.Unauthorized(message())
            else -> BaseException.Unkown(message())
        }
    }
}

fun <T> Resource<T>.doOnSuccess(callback: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) {
        callback.invoke(data)
    }
    return this
}

suspend fun <T> Resource<T>.suspendOnSuccess(callback: suspend (T) -> Unit): Resource<T> {
    if (this is Resource.Success) {
        callback.invoke(data)
    }
    return this
}

fun <T> Resource<T>.doOnError(callback: (Throwable) -> Unit): Resource<T> {
    if (this is Resource.Error) {
        callback.invoke(error)
    }
    return this
}


fun <T> Resource<T>.doOnLoading(callback: () -> Unit): Resource<T> {
    if (this is Resource.Loading) {
        callback.invoke()
    }
    return this
}

fun <T> unsafeLazy(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)
