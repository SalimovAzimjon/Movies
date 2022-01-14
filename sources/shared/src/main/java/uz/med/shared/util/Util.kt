package uz.med.shared.util

import retrofit2.Response
import uz.med.shared.BaseException
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

fun <T> Response<T>.handleResponse(): T {
    val body = body()
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
