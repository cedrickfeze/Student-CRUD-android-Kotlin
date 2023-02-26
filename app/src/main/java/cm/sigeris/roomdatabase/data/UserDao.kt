package cm.sigeris.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cm.sigeris.roomdatabase.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)
    @Update
    fun updateUser(user: User)
    @Query("SELECT * FROM user_table ORDER BY firstName ASC")
    fun readAllData(): LiveData<List<User>>
    @Delete
    suspend fun deleteUser(user: User)
    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
}