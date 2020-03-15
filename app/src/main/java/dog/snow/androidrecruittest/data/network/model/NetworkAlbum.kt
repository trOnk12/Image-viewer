package dog.snow.androidrecruittest.data.network.model

import android.os.Parcelable
import dog.snow.androidrecruittest.domain.model.Album
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NetworkAlbum(
    val id: Int,
    val userId: Int,
    val title: String
) : Parcelable

fun NetworkAlbum.mapToDomain(): Album {
    return Album(id = id, userId = userId, title = title)
}