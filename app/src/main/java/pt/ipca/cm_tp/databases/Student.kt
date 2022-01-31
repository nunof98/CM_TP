package pt.ipca.cm_tp.databases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
class Student(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String
    ) {

}