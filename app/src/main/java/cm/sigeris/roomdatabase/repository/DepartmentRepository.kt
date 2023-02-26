package cm.sigeris.roomdatabase.repository

import androidx.lifecycle.LiveData
import cm.sigeris.roomdatabase.data.DepartmentDAO
import cm.sigeris.roomdatabase.model.Department

class DepartmentRepository (private val departmentDao: DepartmentDAO) {
    val readAllData: LiveData<List<Department>> = departmentDao.readAllData()

    suspend fun addDepartment(department: Department){
        departmentDao.addDepartment(department)
    }

    suspend fun updateDepartment(department: Department){
        departmentDao.updateDepartment(department)
    }

    suspend fun deleteDepartment(department: Department){
        departmentDao.deleteDepartment(department)
    }

    suspend fun deleteAllDepartments(){
        departmentDao.deleteAllDepartments()
    }
}