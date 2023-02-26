package cm.sigeris.roomdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "level")
data class Level(
    @PrimaryKey(autoGenerate = true)
    val idLevel: Int,
    val descLevel:String
):Parcelable
