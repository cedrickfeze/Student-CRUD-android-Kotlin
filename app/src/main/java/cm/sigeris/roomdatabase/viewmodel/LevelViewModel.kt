package cm.sigeris.roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import cm.sigeris.roomdatabase.data.UserDatabase
import cm.sigeris.roomdatabase.model.Level
import cm.sigeris.roomdatabase.repository.LevelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LevelViewModel (application: Application): AndroidViewModel(application)  {


    val readAllData: LiveData<List<Level>>
    private val repository: LevelRepository

    init {
        val levelDao = UserDatabase.getDatabase(application).levelDao()
        repository = LevelRepository(levelDao)
        readAllData = repository.readAllData
    }

    fun addLevel(level: Level){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLevel(level)
        }
    }

    fun updateLevel(level: Level){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLevel(level)
        }
    }

    fun deleteLevel(level: Level){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLevel(level)
        }
    }

    fun deleteAllLevels(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllLevels()
        }
    }
}