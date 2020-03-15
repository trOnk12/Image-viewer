package dog.snow.androidrecruittest.data.network.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


abstract class CallAdapter<T, R> {
    companion object {
        private const val REQUEST_RETRY_LIMIT = 3
    }

    private var retryCount = 0

    suspend operator fun invoke(params: R): T =
        withContext(Dispatchers.IO) {
            suspendCoroutine<T> { continuation ->
                execute(params).enqueue(object : Callback<T> {
                    override fun onFailure(call: Call<T>, t: Throwable) {
                        Log.d("TEST", "onFauilure")
                        Log.d("TEST", "count" + retryCount)
                        if (retryCount < REQUEST_RETRY_LIMIT) {
                            call.clone().enqueue(this)
                            retryCount++
                        } else {
                            continuation.resumeWithException(t)
                        }
                    }

                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                retryCount = 0
                                continuation.resume(it)
                            }
                        }
                    }

                })
            }
        }

    abstract fun execute(params: R): Call<T>

}

class None