package cm.sigeris.roomdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "department")
data class Department(
    @PrimaryKey(autoGenerate = true)
    val iddepartment: Int,
    val namedepartment:String
): Parcelable
