package pt.ipca.cm_tp.databases

import androidx.room.*

@Dao
interface LeaderboardDao {

    @Query("SELECT * FROM leaderboard ORDER BY points DESC")
    fun getAll(): List<Leaderboard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(leaderboard: Leaderboard)

    @Query("DELETE FROM leaderboard")
    fun delete()

    @Update
    fun update(vararg leaderboard: Leaderboard)
}