package pt.ipca.cm_tp.databases

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "deadline")
class Deadline(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dueDate: Date,
    val title: String,
    ) {

}