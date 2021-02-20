package com.example.myapplication.utils

data class Resource<out T>(val data: T?, val status: State, val message: String?){
    companion object{
        fun <T> onSuccess(data: T): Resource<T> = Resource(status = State.SUCCESS, data = data, message = null)

        fun <T> onFailed(data: T?, message: String?): Resource<T> = Resource(status = State.FAILED, data = data, message = message)

        fun <T> onLoading(data: T?): Resource<T> = Resource(status = State.LOADING, data = data, message = null)
    }
}
