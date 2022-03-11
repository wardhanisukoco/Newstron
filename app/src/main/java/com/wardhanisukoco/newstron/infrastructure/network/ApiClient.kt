package com.wardhanisukoco.newstron.infrastructure.network

import com.wardhanisukoco.newstron.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

class ApiClient(private val clientListener: ApiClientListener) {
    private lateinit var retrofit: Retrofit

    fun getClient(): Retrofit {
        val baseUrl: String = BuildConfig.NEWS_API_URL
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(HttpInterceptor(clientListener))
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
        return retrofit
    }


    private class HttpInterceptor(private val listener: ApiClientListener) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
//            val authUtil: AuthUtil = listener.getAuthUtil()
//            var tryCount = 0
            val originalRequest = chain.request()

            val headers = Headers.Builder()
                .add("Authorization", BuildConfig.NEWS_API_KEY)
                .build()

            val newRequest = originalRequest.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .headers(headers)
                .build()

            val response = chain.proceed(newRequest)
            if (response.code == 401) {
                try {
                    listener.handleUnauthorized()
                    return response
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

//            if (newRequest.method != "GET") return response
//
//            while (!response.isSuccessful && tryCount < 3) {
//                tryCount++
//                response.close()
//                response = chain.proceed(newRequest)
//            }

            return response
        }
    }

}