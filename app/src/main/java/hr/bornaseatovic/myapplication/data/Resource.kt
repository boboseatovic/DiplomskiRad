package hr.bornaseatovic.myapplication.data

import hr.shape.data.errors.ErrorType

sealed class Resource<T>(val data: T? = null, val errorType: ErrorType? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(errorType: ErrorType, data: T? = null) : Resource<T>(data, errorType)
}