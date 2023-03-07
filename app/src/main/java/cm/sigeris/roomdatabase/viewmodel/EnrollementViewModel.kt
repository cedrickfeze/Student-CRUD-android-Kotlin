package cm.sigeris.roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import cm.sigeris.roomdatabase.data.UserDatabase
import cm.sigeris.roomdatabase.model.Enrollement
import cm.sigeris.roomdatabase.repository.EnrollementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnrollementViewModel  (application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Enrollement>>
    private val repository: EnrollementRepository

    init {
        val enrollementDao = UserDatabase.getDatabase(application).enrollementDao()
        repository = EnrollementRepository(enrollementDao)
        readAllData = repository.readAllData
    }

    fun addEnrollement(enrollement: Enrollement){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEnrollement(enrollement)
        }
    }

    fun updateEnrollement(enrollement: Enrollement){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEnrollement(enrollement)
        }
    }

    fun deleteEnrollement(enrollement: Enrollement){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEnrollement(enrollement)
        }
    }

    fun deleteAllEnrollements(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllEnrollements()
        }
    }

}