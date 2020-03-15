package dog.snow.androidrecruittest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.data.database.entity.AlbumEntity

@Dao
interface AlbumDao {

    @Query("SELECT * FROM album WHERE id=:id")
    suspend fun getAlbum(id: Int): List<AlbumEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlbum(albumEntity: AlbumEntity)

}