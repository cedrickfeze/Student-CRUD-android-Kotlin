package cm.sigeris.roomdatabase.repository

import androidx.lifecycle.LiveData
import cm.sigeris.roomdatabase.data.EnrollementDao
import cm.sigeris.roomdatabase.model.Enrollement
import cm.sigeris.roomdatabase.model.StudentEnrolled

class EnrollementRepository (private val enrollementDao: EnrollementDao){
    val readAllData: LiveData<List<Enrollement>> = enrollementDao.readAllData()

    suspend fun addEnrollement(enrollement: Enrollement){
        enrollementDao.addEnrollement(enrollement)
    }

    suspend fun updateEnrollement(enrollement: Enrollement){
        enrollementDao.updateEnrollement(enrollement)
    }

    suspend fun deleteEnrollement(enrollement: Enrollement){
        enrollementDao.deleteEnrollement(enrollement)
    }

    suspend fun deleteAllEnrollements(){
        enrollementDao.deleteAllEnrollements()
    }

    suspend fun ReadEnrolledStudents(): LiveData<List<StudentEnrolled>>{
        return enrollementDao.ReadEnrolledStudents()
    }
}