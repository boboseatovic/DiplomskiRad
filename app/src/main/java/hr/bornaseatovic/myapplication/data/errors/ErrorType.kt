package hr.shape.data.errors

sealed interface ErrorType{
    object Error404 : ErrorType
    object Error400 : ErrorType
    class UnknownError(val message: String) : ErrorType
}