package cm.sigeris.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import cm.sigeris.roomdatabase.model.Department

@Dao
interface DepartmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDepartment(department: Department)

    @Update
    fun updateDepartment(department: Department)

    @Delete
    suspend fun deleteDepartment(department: Department)

    @Query("DELETE FROM department")
    suspend fun deleteAllDepartments()

    @Query("SELECT * FROM department")
    fun getAllDepartments(): List<Department>

    @Query("SELECT * FROM department ORDER BY namedepartment ASC")
    fun readAllData(): LiveData<List<Department>>
}