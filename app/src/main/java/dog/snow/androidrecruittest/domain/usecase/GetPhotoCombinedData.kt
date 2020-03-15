package dog.snow.androidrecruittest.domain.usecase

import dog.snow.androidrecruittest.core.interactor.None
import dog.snow.androidrecruittest.core.interactor.UseCase
import dog.snow.androidrecruittest.data.network.repository.AlbumRepository
import dog.snow.androidrecruittest.data.network.repository.PhotoRepository
import dog.snow.androidrecruittest.domain.model.Album
import dog.snow.androidrecruittest.domain.model.Photo
import javax.inject.Inject

class GetPhotoCombinedData
@Inject constructor(
    private val albumRepository: AlbumRepository,
    private val photoRepository: PhotoRepository
) : UseCase<List<PhotoCombinedData>, None>() {

    override suspend fun run(params: None): List<PhotoCombinedData> {
        return photoRepository.getPhotos().map { photo ->
            PhotoCombinedData(albumRepository.getAlbum(photo.albumId), photo)
        }
    }

}

data class PhotoCombinedData(
    val album: Album,
    val photo: Photo
)