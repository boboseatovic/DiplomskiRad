package hr.shape.data.errors

import retrofit2.HttpException

object ErrorParser {

    fun parseError(throwable: Throwable): ErrorType {
        if (throwable is HttpException) {
            return when (throwable.code()) {
                400 -> ErrorType.Error404
                404 -> ErrorType.Error400
                else -> ErrorType.UnknownError(throwable.message ?: "Unknown error message")
            }
        }
        return ErrorType.UnknownError(throwable.message ?: "Unknown error message")
    }
}