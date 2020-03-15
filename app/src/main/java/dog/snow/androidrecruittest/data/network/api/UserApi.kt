package dog.snow.androidrecruittest.data.network.api

import dog.snow.androidrecruittest.data.network.model.NetworkUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<NetworkUser>

}