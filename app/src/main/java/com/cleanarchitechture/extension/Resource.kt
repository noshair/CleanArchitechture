package com.cleanarchitechture.extension

sealed class Resource<T>(val data: T? = null, val error: String? = null) {
    class OnSuccess<T>(data: T?) : Resource<T>(data)
    class OnFailure<T>(data: T? = null, error: String?) : Resource<T>(data, error)
    class OnLoading<T> : Resource<T>()
    class OnNothing<T> : Resource<T>()
}