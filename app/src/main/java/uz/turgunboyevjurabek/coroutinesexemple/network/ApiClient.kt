package uz.turgunboyevjurabek.coroutinesexemple.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    const val BASE_URL="https://cbu.uz/uz/arkhiv-kursov-valyut/"


    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    fun getApiService():ApiService{
        return getRetrofit().create(ApiService::class.java)
    }

}
