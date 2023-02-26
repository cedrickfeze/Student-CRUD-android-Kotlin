package cm.sigeris.roomdatabase.repository

import androidx.lifecycle.LiveData
import cm.sigeris.roomdatabase.data.SpecialtyDao
import cm.sigeris.roomdatabase.model.Specialty


class SpecialtyRepository (private val specialtyDao: SpecialtyDao) {
    val readAllData: LiveData<List<Specialty>> = specialtyDao.readAllData()

    suspend fun addSpecialty(specialty: Specialty){
        specialtyDao.addSpecialty(specialty)
    }

    suspend fun updateSpecialty(specialty: Specialty){
        specialtyDao.updateSpecialty(specialty)
    }

    suspend fun deleteSpecialty(specialty: Specialty){
        specialtyDao.deleteSpecialty(specialty)
    }

    suspend fun deleteAllSpecialties(){
        specialtyDao.deleteAllSpecialties()
    }
}