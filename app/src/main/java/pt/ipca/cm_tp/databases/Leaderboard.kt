package pt.ipca.cm_tp.databases

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "leaderboard")
class Leaderboard(
    @PrimaryKey(autoGenerate = false)
    val studentNumber: Int,
    val studentName: String,
    val points: Int
    ) {

}