package pt.ipca.cm_tp.databases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "deadline")
class Deadline(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val dueDate: String,
    val description: String,
    //@ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    //val picture: ByteArray?
    ) {

}