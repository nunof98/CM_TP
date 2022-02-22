package pt.ipca.cm_tp.databases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.Inet4Address

@Entity(tableName = "student")
class Student(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val course: String,
    val year: Int,
    //val email: String,
    //val phoneNumber: String,
    //val address: String,
    //val status: String,
    //val averageGrade: Int,
    //@ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    //var profilePicture: ByteArray?
    ) {

}