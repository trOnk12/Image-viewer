package dog.snow.androidrecruittest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dog.snow.androidrecruittest.data.database.dao.AlbumDao
import dog.snow.androidrecruittest.data.database.dao.PhotoDao
import dog.snow.androidrecruittest.data.database.dao.UserDao
import dog.snow.androidrecruittest.data.database.entity.AlbumEntity
import dog.snow.androidrecruittest.data.database.entity.PhotoEntity
import dog.snow.androidrecruittest.data.database.entity.UserEntity

@Database(
    entities = [AlbumEntity::class, PhotoEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDataBase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    abstract fun photoDao(): PhotoDao

    abstract fun userDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RoomDataBase? = null

        fun getDatabase(context: Context): RoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDataBase::class.java,
                    "album_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}