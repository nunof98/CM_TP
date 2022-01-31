package pt.ipca.cm_tp.databases

import androidx.room.*

@Dao
interface StudentDao {

    @Query("SELECT * FROM student")
    fun getAll(): List<Student>

    @Query("SELECT name FROM student WHERE id == :id")
    fun getName(id: Int): String

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg student: Student)

    @Delete
    fun delete(student: Student)

    @Update
    fun update(vararg student: Student)
}