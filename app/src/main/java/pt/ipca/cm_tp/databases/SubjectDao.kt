package pt.ipca.cm_tp.databases

import androidx.room.*

@Dao
interface SubjectDao {

    @Query("SELECT * FROM subject")
    fun getAll(): List<Subject>

    @Query("SELECT * FROM subject WHERE course = :course AND year = :year")
    fun getSubjectsByCourseAndYear(course: String, year: Int): List<Subject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubject(subject: Subject)

    @Query("DELETE FROM subject WHERE name = :name and course = :course")
    fun deleteByName(name: String, course: String)

    @Update
    fun update(vararg subjects: Subject)
}