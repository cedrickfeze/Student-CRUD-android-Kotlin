package cm.sigeris.roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import cm.sigeris.roomdatabase.data.UserDatabase
import cm.sigeris.roomdatabase.model.Specialty
import cm.sigeris.roomdatabase.repository.SpecialtyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpecialtyViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Specialty>>
    private val repository: SpecialtyRepository

    init {
        val specialtyDao = UserDatabase.getDatabase(application).specialtyDao()
        repository = SpecialtyRepository(specialtyDao)
        readAllData = repository.readAllData
    }

    fun addSpecialty(specialty: Specialty){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSpecialty(specialty)
        }
    }

    fun updateSpecialty(specialty: Specialty){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSpecialty(specialty)
        }
    }

    fun deleteSpecialty(specialty: Specialty){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSpecialty(specialty)
        }
    }

    fun deleteAllSpecialtys(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllSpecialties()
        }
    }
}