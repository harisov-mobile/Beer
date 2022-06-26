package ru.internetcloud.beer.data.network.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.internetcloud.beer.BuildConfig

private const val BASE_URL = "https://api.punkapi.com/v2/"

class ApiClient {

    private val okHttpClient: OkHttpClient =
        if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY })
                // .addInterceptor(BasicAuthInterceptor(authParameters.login, authParameters.password))
                .build()
        } else {
            OkHttpClient.Builder()
                // .addInterceptor(BasicAuthInterceptor(authParameters.login, authParameters.password))
                .build()
        }

    // необходимо эту переменную поместить ниже, чем okHttpClient
    // т.к. сначала должна пройти инициализация переменной okHttpClient
    val client: ApiService = getRetrofit().create(ApiService::class.java)

    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }
}
