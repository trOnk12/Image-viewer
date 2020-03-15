package dog.snow.androidrecruittest.data.network.service

import dog.snow.androidrecruittest.data.network.api.UserApi
import dog.snow.androidrecruittest.data.network.model.NetworkUser
import dog.snow.androidrecruittest.data.network.utils.CallAdapter
import retrofit2.Call
import javax.inject.Inject

class UserService
@Inject constructor(
    private val userApi: UserApi
) : CallAdapter<NetworkUser, Int>() {

    override fun execute(params: Int): Call<NetworkUser> =
         userApi.getUser(params)

}

data class UserId(val id: Int)