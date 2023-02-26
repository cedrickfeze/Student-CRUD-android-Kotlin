package cm.sigeris.roomdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


    @Parcelize
    @Entity(tableName = "specialty",
    foreignKeys = [ForeignKey(
        entity = Department::class,
        childColumns = ["id_department"],
        parentColumns = ["iddepartment"]
    )])
    data class Specialty(
    @PrimaryKey(autoGenerate = true)
    val idSpecialty: Int,
    val nameSpecialty: String,
    val descSpecialty: String,
    val id_department: Int
    ): Parcelable
