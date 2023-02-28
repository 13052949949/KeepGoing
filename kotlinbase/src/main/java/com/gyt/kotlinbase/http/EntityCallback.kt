package com.gyt.kotlinbase.http

interface EntityCallback<T> {
    fun onSuccess(entity: T)
    fun onFailure(message: String)
}