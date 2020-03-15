package dog.snow.androidrecruittest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.data.database.entity.AlbumEntity
import dog.snow.androidrecruittest.data.database.entity.PhotoEntity

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo")
    suspend fun getPhotos(): List<PhotoEntity>

    @Query("SELECT * FROM photo WHERE id=:id")
    suspend fun getPhoto(id: Int): List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhoto(photos: PhotoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhotos(photoEntities: List<PhotoEntity>)

}