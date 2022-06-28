package ru.internetcloud.beer.domain.model

sealed class State<T>(
    val data: T? = null,
    val exception: Exception? = null
) {
    class Loading<T> : State<T>()

    class Success<T>(data: T) : State<T>(data)

    class Error<T>(ex: Exception? = null) : State<T>(exception = ex)
}
