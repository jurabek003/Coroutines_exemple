package uz.turgunboyevjurabek.coroutinesexemple.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URl="https://omborapi11.pythonanywhere.com/"

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URl)
            .build()
    }
    
}
