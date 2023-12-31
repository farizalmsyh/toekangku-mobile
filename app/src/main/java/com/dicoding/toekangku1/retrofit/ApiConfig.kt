package com.dicoding.toekangku1.retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object ApiConfig {

    fun getApiService(): ApiService {
        // Logger untuk debugging request dan response
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // OkHttpClient dengan interceptor logging
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // Membangun instance Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://toekangku-backend-5mballdzpa-et.a.run.app/v1/mobile/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}