package cm.sigeris.roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cm.sigeris.roomdatabase.model.*

@Database(entities = [User::class, Level::class, Department::class, Specialty::class, Enrollement::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun levelDao(): LevelDao

    abstract fun departmentDao(): DepartmentDAO

    abstract fun specialtyDao(): SpecialtyDao

    abstract fun enrollementDao(): EnrollementDao




    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance

                val departmentDao = instance.departmentDao()
            }
        }
    }
}