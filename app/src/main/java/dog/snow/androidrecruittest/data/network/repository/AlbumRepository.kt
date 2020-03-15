package dog.snow.androidrecruittest.data.network.repository

import dog.snow.androidrecruittest.data.database.dao.AlbumDao
import dog.snow.androidrecruittest.data.database.entity.AlbumEntity
import dog.snow.androidrecruittest.data.network.model.mapToDomain
import dog.snow.androidrecruittest.data.network.service.AlbumService
import dog.snow.androidrecruittest.domain.model.Album
import dog.snow.androidrecruittest.domain.repository.IAlbumRepository
import javax.inject.Inject

class AlbumRepository
@Inject constructor(
    private val albumService: AlbumService,
    private val albumDao: AlbumDao
) : IAlbumRepository {

    override suspend fun getAlbum(id: Int): Album {
        val albums = albumDao.getAlbum(id)

        return if (albums.isEmpty()) {
            albumService(id).also { album ->
                albumDao.insertAlbum(AlbumEntity(album.id, album.userId, album.title))
            }.mapToDomain()
        } else {
            albums.map { Album(it.id, it.userId, it.title) }.first()
        }
    }

}

