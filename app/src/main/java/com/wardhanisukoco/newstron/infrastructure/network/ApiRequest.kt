package com.wardhanisukoco.newstron.infrastructure.network

import android.util.Log
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.CoroutineContext

object ApiRequest {
     inline fun <T> safeApiCall(apiCall: () -> Response<T>): ApiResponse<T> {
        return try {
            val response = apiCall.invoke()

            if (response.code() == 200) {
                ApiResponse.success(response)
            } else {
//                ApiResponse.error(Exception(response.toString()))
                throw Exception(response.toString())
            }
        } catch (e: Exception) {
            ApiResponse.failure(e)
        }
    }
}