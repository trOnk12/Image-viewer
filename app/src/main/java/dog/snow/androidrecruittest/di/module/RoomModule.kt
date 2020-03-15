package dog.snow.androidrecruittest.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dog.snow.androidrecruittest.data.database.RoomDataBase
import dog.snow.androidrecruittest.data.database.dao.AlbumDao
import dog.snow.androidrecruittest.data.database.dao.PhotoDao
import dog.snow.androidrecruittest.data.database.dao.UserDao
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDataBase(context: Context): RoomDataBase {
        return RoomDataBase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideUserDao(roomDataBase: RoomDataBase): UserDao {
        return roomDataBase.userDao()
    }

    @Singleton
    @Provides
    fun providePhotoDao(roomDataBase: RoomDataBase): PhotoDao {
        return roomDataBase.photoDao()
    }

    @Singleton
    @Provides
    fun provideAlbumDao(roomDataBase: RoomDataBase): AlbumDao {
        return roomDataBase.albumDao()
    }

}