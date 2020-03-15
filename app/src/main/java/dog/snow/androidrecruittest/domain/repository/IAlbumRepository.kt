package dog.snow.androidrecruittest.domain.repository

import dog.snow.androidrecruittest.domain.model.Album

interface IAlbumRepository {
    suspend fun getAlbum(id: Int): Album
}