package cm.sigeris.roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import cm.sigeris.roomdatabase.data.UserDatabase
import cm.sigeris.roomdatabase.model.Department
import cm.sigeris.roomdatabase.repository.DepartmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DepartmentViewModel (application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Department>>
    private val repository: DepartmentRepository

    init {
        val departmentDao = UserDatabase.getDatabase(application).departmentDao()
        repository = DepartmentRepository(departmentDao)
        readAllData = repository.readAllData
    }

    fun addDepartment(department: Department){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDepartment(department)
        }
    }

    fun updateDepartment(department: Department){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDepartment(department)
        }
    }

    fun deleteDepartment(department: Department){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDepartment(department)
        }
    }

    fun deleteAllDepartments(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDepartments()
        }
    }
}