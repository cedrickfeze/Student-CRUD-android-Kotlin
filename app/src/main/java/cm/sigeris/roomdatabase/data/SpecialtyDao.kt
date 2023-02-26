package cm.sigeris.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import cm.sigeris.roomdatabase.model.Specialty

@Dao
interface SpecialtyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSpecialty(specialty: Specialty)

    @Update
    fun updateSpecialty(specialty: Specialty)

    @Delete
    suspend fun deleteSpecialty(specialty: Specialty)

    @Query("DELETE FROM specialty")
    suspend fun deleteAllSpecialties()

    @Query("SELECT * FROM specialty")
    fun getAllSpecialties(): List<Specialty>

    @Query("SELECT * FROM specialty ORDER BY nameSpecialty ASC")
    fun readAllData(): LiveData<List<Specialty>>

}