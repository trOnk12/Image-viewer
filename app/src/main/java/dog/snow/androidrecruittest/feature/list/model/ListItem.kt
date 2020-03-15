package dog.snow.androidrecruittest.feature.list.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItem(
    val photoInfo: PhotoInfo,
    val albumInfo: AlbumInfo
) : Parcelable {
    @Parcelize
    data class PhotoInfo(
        val id: Int,
        val title: String,
        val thumbnailUrl: String
    ) : Parcelable

    @Parcelize
    data class AlbumInfo(
        val thumbnailUrl: String
    ) : Parcelable
}