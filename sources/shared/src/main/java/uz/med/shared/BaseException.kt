package uz.med.shared

sealed class BaseException(message: String) : Exception(message) {
    class ServerError(message: String) : BaseException(message)
    class Unauthorized(message: String) : BaseException(message)
    class Unkown(message: String):BaseException(message)
}
