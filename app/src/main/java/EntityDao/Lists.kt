package EntityDao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lists")
data class Lists(
@PrimaryKey(autoGenerate = true) val iD:Int= 0,
val name:String,

)
