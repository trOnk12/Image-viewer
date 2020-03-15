package dog.snow.androidrecruittest.data.network.service

import dog.snow.androidrecruittest.data.network.api.AlbumApi
import dog.snow.androidrecruittest.data.network.model.NetworkAlbum
import dog.snow.androidrecruittest.data.network.utils.CallAdapter
import retrofit2.Call
import javax.inject.Inject

class AlbumService
@Inject constructor(
    private val albumApi: AlbumApi
) : CallAdapter<NetworkAlbum, Int>() {

    override fun execute(params: Int): Call<NetworkAlbum> =
        albumApi.getAlbum(params)

}




