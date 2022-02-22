package pt.ipca.cm_tp.databases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
class Subject(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val course: String,
    val semester: Int,
    val year: Int,
    val schedule1: String,
    val schedule2: String,
    val professorName: String,
    val professorEmail: String,
    val officeHours: String,
    //@ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    //var professorPicture: ByteArray?
    ) {

}