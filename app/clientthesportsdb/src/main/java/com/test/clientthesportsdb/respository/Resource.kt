package com.test.clientthesportsdb.respository

sealed class Resource<T>(open val value: T?)
data class SuccessResource<T>(override val value: T) : Resource<T>(value)
data class LoadingResource<T>(override val value: T? = null) : Resource<T>(value)
class ErrorResource<Any>(val errorCode: Int = 0) : Resource<Any>(null)


