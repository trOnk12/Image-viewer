package dog.snow.androidrecruittest.data.network.api

import dog.snow.androidrecruittest.data.network.model.NetworkAlbum
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApi {

    @GET("albums/{id}")
    fun getAlbum(@Path("id") id: Int): Call<NetworkAlbum>

}