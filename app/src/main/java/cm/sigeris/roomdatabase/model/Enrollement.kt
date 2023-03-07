package cm.sigeris.roomdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "enrollement",
    foreignKeys = [

            ForeignKey(
            entity = User::class,
            childColumns = ["id_student"],
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        ),
            ForeignKey(
                entity = Level::class,
                childColumns = ["id_level"],
                parentColumns = ["idLevel"],
                onDelete = ForeignKey.CASCADE
            ),

            ForeignKey(
                entity = Specialty::class,
            childColumns = ["id_specialty"],
            parentColumns = ["idSpecialty"],
            onDelete = ForeignKey.CASCADE
    )


    ])

data class Enrollement(
    @PrimaryKey(autoGenerate = true)
    val idEnrollement: Int,
    val yearEnrollement: String,
    val id_specialty: Int,
    val id_level: Int,
    val id_student: Int
): Parcelable

@Parcelize
data class StudentEnrolled(
    val idEnrollement: Int,
    val id_specialty: Int,
    val id_level: Int,
    val id_student: Int,
    val yearEnrollement: String,
    val nameSpecialty: String,
    val descSpecialty: String,
    val id_department: Int,
    val nameLevel: String,
    val descLevel: String,
    val id_user: Int,
    val nameUser: String,
): Parcelable
