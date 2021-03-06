package pt.ipca.cm_tp.databases

import androidx.room.*

@Dao
interface StudentDao {

    @Query("SELECT * FROM student")
    fun getAll(): List<Student>

    @Query("SELECT (firstName || ' ' || lastName) FROM student WHERE id = :id")
    fun getNameById(id: Int): String

    @Query("SELECT course FROM student WHERE id = :id")
    fun getCourseById(id: Int): String

    @Query("SELECT * FROM student WHERE id = :id ")
    fun findStudentById(id: Int): Student

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student)

    @Query("DELETE FROM student WHERE id = :id")
    fun deleteById(id: Int)

    @Update
    fun update(vararg student: Student)
}