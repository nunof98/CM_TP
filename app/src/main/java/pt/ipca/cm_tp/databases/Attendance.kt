package pt.ipca.cm_tp.databases

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "attendance")
class Attendance(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val subject: String,
    val date: String,
    val start: String,
    val end: String,
    val summaryInfo: String
    ) {

}