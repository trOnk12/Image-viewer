package dog.snow.androidrecruittest.domain.repository

import dog.snow.androidrecruittest.domain.model.Photo

interface IPhotoRepository {
    suspend fun getPhotos(): List<Photo>
    suspend fun getPhoto(id: Int): Photo
}