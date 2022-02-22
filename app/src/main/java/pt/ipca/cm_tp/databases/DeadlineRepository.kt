package pt.ipca.cm_tp.databases

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class DeadlineRepository(private val deadlineDao: DeadlineDao) {

    init {

    }

    fun getAll(callback: (deadlineList: List<Deadline>?) -> Unit) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                callback(deadlineDao.getAll())
            }
    }

    fun insertDeadline(deadline: Deadline) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                deadlineDao.insertDeadline(deadline)
            }
    }

    fun deleteById(id: Int) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                deadlineDao.deleteById(id)
            }
    }
}