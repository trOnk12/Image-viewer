package dog.snow.androidrecruittest.data.network.repository

import dog.snow.androidrecruittest.data.database.dao.PhotoDao
import dog.snow.androidrecruittest.data.database.entity.PhotoEntity
import dog.snow.androidrecruittest.data.network.model.NetworkPhoto
import dog.snow.androidrecruittest.data.network.service.PhotoService
import dog.snow.androidrecruittest.data.network.utils.None
import dog.snow.androidrecruittest.domain.model.Photo
import dog.snow.androidrecruittest.domain.repository.IPhotoRepository
import javax.inject.Inject

class PhotoRepository
@Inject constructor(
    private val photoService: PhotoService,
    private val photoDao: PhotoDao
) : IPhotoRepository {

    override suspend fun getPhotos(): List<Photo> {
        val photos = photoDao.getPhotos()

        return if (photos.isEmpty()) {
            photoService(None()).also { photo ->
                photoDao.insertPhotos(photo.map { it.mapToEntity() })
            }.map { it.mapToDomain() }
        } else {
            photos.map { Photo(it.id, it.albumId, it.title, it.url, it.thumbnailUrl) }
        }
    }

    override suspend fun getPhoto(id: Int): Photo {
        return photoDao.getPhoto(id).map {
            Photo(it.id, it.albumId, it.title, it.url, it.thumbnailUrl)
        }.first()
    }

}

fun NetworkPhoto.mapToDomain(): Photo {
    return Photo(id = id, albumId = albumId, title = title, url = url, thumbnailUrl = thumbnailUrl)
}

fun NetworkPhoto.mapToEntity(): PhotoEntity {
    return PhotoEntity(
        primaryId = id,
        id = id,
        albumId = albumId,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}