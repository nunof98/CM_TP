package pt.ipca.cm_tp.databases

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "attendance")
class Leaderboard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val position: Int,
    val studentName: String,
    val points: Int
    ) {

}