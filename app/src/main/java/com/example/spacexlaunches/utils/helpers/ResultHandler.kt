package com.example.spacexlaunches.utils.helpers

const val EMPTY_DEFAULT_MESSAGE = "No results found"

sealed class ResultHandler<out T : Any>{
    class Success<out T : Any>(val data: T) : ResultHandler<T>()
    class Error(val exception: Throwable) : ResultHandler<Nothing>()
    class Empty(val emptyMessage: String = EMPTY_DEFAULT_MESSAGE) : ResultHandler<Nothing>()
}