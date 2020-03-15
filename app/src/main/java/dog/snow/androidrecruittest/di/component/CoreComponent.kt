package dog.snow.androidrecruittest.di.component

import androidx.lifecycle.ViewModelProvider
import dog.snow.androidrecruittest.di.module.viewmodel.ViewModelModule
import dagger.Component
import dog.snow.androidrecruittest.data.network.repository.AlbumRepository
import dog.snow.androidrecruittest.data.network.repository.PhotoRepository
import dog.snow.androidrecruittest.data.network.repository.UserRepository
import dog.snow.androidrecruittest.di.module.ContextModule
import dog.snow.androidrecruittest.di.module.NetworkModule
import dog.snow.androidrecruittest.di.module.RepositoryModule
import dog.snow.androidrecruittest.di.module.RoomModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, ViewModelModule::class, NetworkModule::class, RoomModule::class, RepositoryModule::class])
interface CoreComponent {
    fun viewModelProviderFactory(): ViewModelProvider.Factory

    fun userRepository(): UserRepository

    fun photoRepository(): PhotoRepository

    fun albumRepository(): AlbumRepository
}