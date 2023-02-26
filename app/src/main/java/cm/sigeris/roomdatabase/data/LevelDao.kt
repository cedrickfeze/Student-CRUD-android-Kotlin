package cm.sigeris.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import cm.sigeris.roomdatabase.model.Level
import cm.sigeris.roomdatabase.model.User

@Dao
interface LevelDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLevel(level: Level)

    @Update
    fun updateLevel(level: Level)

    @Delete
    suspend fun deleteLevel(level: Level)

    @Query("DELETE FROM level")
    suspend fun deleteAllLevels()

    @Query("SELECT * FROM level ORDER BY descLevel ASC")
    fun readAllData(): LiveData<List<Level>>
}