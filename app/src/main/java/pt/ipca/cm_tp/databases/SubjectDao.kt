package pt.ipca.cm_tp.databases

import androidx.room.*

@Dao
interface SubjectDao {

    @Query("SELECT * FROM subjects")
    fun getAll(): List<Subject>

    @Query("SELECT * FROM subjects WHERE course = :course")
    fun getCourseSubjects(course: String): List<Subject>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg subjects: Array<out Subject>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSubject(subject: Subject)

    @Query("DELETE FROM subjects WHERE name = :name and course = :course")
    fun deleteByName(name: String, course: String)

    @Update
    fun update(vararg subjects: Subject)
}