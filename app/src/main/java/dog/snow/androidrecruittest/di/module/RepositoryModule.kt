package dog.snow.androidrecruittest.di.module

import dagger.Binds
import dagger.Module
import dog.snow.androidrecruittest.data.network.repository.AlbumRepository
import dog.snow.androidrecruittest.data.network.repository.PhotoRepository
import dog.snow.androidrecruittest.data.network.repository.UserRepository
import dog.snow.androidrecruittest.domain.repository.IAlbumRepository
import dog.snow.androidrecruittest.domain.repository.IPhotoRepository
import dog.snow.androidrecruittest.domain.repository.IUserRepository

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideUseRepository(userRepository: UserRepository): IUserRepository

    @Binds
    abstract fun providePhotoRepository(photoRepository: PhotoRepository): IPhotoRepository

    @Binds
    abstract fun provideAlbumRepository(albumRepository: AlbumRepository): IAlbumRepository

}