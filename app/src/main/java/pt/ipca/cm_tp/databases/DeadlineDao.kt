package pt.ipca.cm_tp.databases

import androidx.room.*

@Dao
interface DeadlineDao {

    @Query("SELECT * FROM deadline ORDER BY dueDate")
    fun getAll(): List<Deadline>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeadline(deadline: Deadline)

    @Query("DELETE FROM deadline WHERE id = :id")
    fun deleteById(id: Int)

    @Update
    fun update(vararg deadline: Deadline)
}