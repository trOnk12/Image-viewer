package dog.snow.androidrecruittest.domain.usecase

import dog.snow.androidrecruittest.core.interactor.UseCase
import dog.snow.androidrecruittest.data.network.repository.AlbumRepository
import dog.snow.androidrecruittest.data.network.repository.PhotoRepository
import dog.snow.androidrecruittest.data.network.repository.UserRepository
import dog.snow.androidrecruittest.domain.model.Album
import dog.snow.androidrecruittest.domain.model.Photo
import dog.snow.androidrecruittest.domain.model.User
import javax.inject.Inject

class GetAlbumCombinedData @Inject constructor(
    private val userRepository: UserRepository,
    private val albumRepository: AlbumRepository,
    private val photoRepository: PhotoRepository
) : UseCase<AlbumCombinedData, Int>() {

    override suspend fun run(params: Int): AlbumCombinedData {
        photoRepository.getPhoto(params).also { photo ->
            val album = albumRepository.getAlbum(photo.albumId)
            val user = userRepository.getUser(album.userId)

            return AlbumCombinedData(album, photo, user)
        }
    }

}

data class AlbumCombinedData(
    val album: Album,
    val photo: Photo,
    val user: User
)