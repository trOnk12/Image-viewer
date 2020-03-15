package dog.snow.androidrecruittest.data.network.service

import dog.snow.androidrecruittest.data.network.api.PhotoApi
import dog.snow.androidrecruittest.data.network.model.NetworkPhoto
import dog.snow.androidrecruittest.data.network.utils.CallAdapter
import dog.snow.androidrecruittest.data.network.utils.None
import retrofit2.Call
import javax.inject.Inject

class PhotoService
@Inject constructor(
    private val photoApi: PhotoApi
): CallAdapter<List<NetworkPhoto>, None>() {

    override fun execute(params: None): Call<List<NetworkPhoto>> =
        photoApi.getPhotos()

}
