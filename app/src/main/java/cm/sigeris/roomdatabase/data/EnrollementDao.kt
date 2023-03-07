package cm.sigeris.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import cm.sigeris.roomdatabase.model.Enrollement
import cm.sigeris.roomdatabase.model.StudentEnrolled


@Dao
interface EnrollementDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEnrollement(enrollement: Enrollement)

    @Update
    fun updateEnrollement(enrollement: Enrollement)

    @Delete
    suspend fun deleteEnrollement(enrollement: Enrollement)

    @Query("DELETE FROM enrollement")
    suspend fun deleteAllEnrollements()

    @Query("SELECT * FROM enrollement")
    fun getAllEnrollements(): List<Enrollement>

    @Query("SELECT * FROM enrollement ORDER BY yearEnrollement ASC")
    fun readAllData(): LiveData<List<Enrollement>>

    @Transaction
    @Query("SELECT * FROM enrollement " +
            "INNER JOIN specialty ON enrollement.id_specialty = specialty.idSpecialty" +
            "INNER JOIN level ON enrollement.id_level = level.idLevel"+
            "INNER JOIN user ON enrollement.id_student = user.id")
    fun ReadEnrolledStudents(): LiveData<List<StudentEnrolled>>


}