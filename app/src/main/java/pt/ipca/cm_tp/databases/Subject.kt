package pt.ipca.cm_tp.databases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
class Subject(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val course: String,
    val semester: Int,
    val year: Int,
    val schedule1: String,
    val schedule2: String,
    val professorName: String,
    val professorEmail: String,
    val officeHours: String
    ) {

}