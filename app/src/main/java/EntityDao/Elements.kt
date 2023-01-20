package EntityDao


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "elements")
data class Elements(
    @PrimaryKey(autoGenerate = true) val iD:Int= 0,
   val name:String,
    val date:String,
    val topic_id:Int

)
