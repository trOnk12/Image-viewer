package dog.snow.androidrecruittest.domain.usecase

import dog.snow.androidrecruittest.core.interactor.NoTypeUseCase
import dog.snow.androidrecruittest.core.interactor.None
import dog.snow.androidrecruittest.data.network.repository.AlbumRepository
import dog.snow.androidrecruittest.data.network.repository.UserRepository
import dog.snow.androidrecruittest.domain.repository.IPhotoRepository
import javax.inject.Inject

class DownloadPhotoDataUseCase
@Inject constructor(
    private val photoRepository: IPhotoRepository,
    private val albumRepository: AlbumRepository,
    private val userRepository: UserRepository
) : NoTypeUseCase<None>() {

    override suspend fun run(params: None) {
        photoRepository.getPhotos().forEach { photo ->
            albumRepository.getAlbum(photo.albumId).let { album ->
                userRepository.getUser(album.userId)
            }
        }
    }

}