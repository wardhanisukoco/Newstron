package com.wardhanisukoco.newstron.infrastructure.network

import retrofit2.Response

data class ApiResponse<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?
) {

    companion object {
        fun <T> success(data: Response<T>): ApiResponse<T> {
            return ApiResponse(
                status = Status.Success,
                data = data,
                exception = null
            )
        }

        fun <T> failure(exception: Exception): ApiResponse<T> {
            return ApiResponse(
                status = Status.Failure,
                data = null,
                exception = exception
            )
        }
        fun <T> error(exception: Exception): ApiResponse<T> {
            return ApiResponse(
                status = Status.Error,
                data = null,
                exception = exception
            )
        }
    }

    sealed class Status {
        object Success : Status()
        object Failure : Status()
        object Error : Status()
    }

    val failed: Boolean
        get() = this.status == Status.Failure

    val error: Boolean
        get() = this.status == Status.Failure

    val isSuccessful: Boolean
        get() = !failed && !error && this.data?.isSuccessful == true

    val body: T
        get() = this.data!!.body()!!

    val bodyNullable: T?
        get() = this.data?.body()
}