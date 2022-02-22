package pt.ipca.cm_tp.databases

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class LeaderboardRepository(private val leaderboardDao: LeaderboardDao) {

    init {

    }

    fun getAll(callback: (leaderboardList: List<Leaderboard>?) -> Unit) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                callback(leaderboardDao.getAll())
            }
    }

    fun insert(leaderboard: Leaderboard) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                leaderboardDao.insert(leaderboard)
            }
    }

    fun delete() {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                leaderboardDao.delete()
            }
    }
}