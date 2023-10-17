package uz.turgunboyevjurabek.coroutinesexemple.network

import retrofit2.http.GET

import uz.turgunboyevjurabek.coroutinesexemple.madels.Valyuta_get

interface ApiService {
    @GET("json/")
    suspend fun getClients():ArrayList<Valyuta_get>

}