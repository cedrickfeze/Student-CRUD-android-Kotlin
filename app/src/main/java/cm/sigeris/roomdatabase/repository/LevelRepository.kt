package cm.sigeris.roomdatabase.repository

import androidx.lifecycle.LiveData
import cm.sigeris.roomdatabase.data.LevelDao
import cm.sigeris.roomdatabase.data.UserDao
import cm.sigeris.roomdatabase.model.Level
import cm.sigeris.roomdatabase.model.User

class LevelRepository(private val levelDao: LevelDao) {
    val readAllData: LiveData<List<Level>> = levelDao.readAllData()

    suspend fun addLevel(level: Level){
        levelDao.addLevel(level)
    }

    suspend fun updateLevel(level: Level){
        levelDao.updateLevel(level)
    }

    suspend fun deleteLevel(level: Level){
        levelDao.deleteLevel(level)
    }

    suspend fun deleteAllLevels(){
        levelDao.deleteAllLevels()
    }
}