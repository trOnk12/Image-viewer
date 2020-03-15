package dog.snow.androidrecruittest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.data.database.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id=:id")
    suspend fun getUser(id: Int): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userEntity: UserEntity)

}