package dog.snow.androidrecruittest.data.network.api

import dog.snow.androidrecruittest.data.network.model.NetworkPhoto
import retrofit2.Call
import retrofit2.http.GET

interface PhotoApi {

    @GET("photos?_limit=100")
    fun getPhotos(): Call<List<NetworkPhoto>>

}