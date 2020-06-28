package com.nyayadhish.droidgenesiskotlin.lib.retrofit


import com.nyayadhish.droidgenesiskotlin.lib.base.*
import com.nyayadhish.droidgenesiskotlin.lib.utils.Constants
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 * Created by Nikhil Nyayadhish
 */
interface APIService {

    /**
     * Get the instance of Base URL.
     */
    companion object {
        fun getDashboardBaseUrl(): APIService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(getClient())
                .baseUrl(Constants.DASHBOARD_BASE_URL)
                .build()
            return retrofit.create(APIService::class.java)
        }

        fun getOnBoardingBaseUrl(): APIService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .client(getClient())
                .baseUrl(Constants.ONBOARDING_BASE_URL)
                .build()
            return retrofit.create(APIService::class.java)
        }

        /**
         * @return Instance of OkHttpClient class with modified timeout
         */
        private fun getClient(): OkHttpClient {
            val httpTimeout: Long = 80
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.connectTimeout(httpTimeout, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(httpTimeout, TimeUnit.SECONDS)
            return okHttpClientBuilder.build()
        }
    }

    /**
     * API
     */


}
