package pt.ipca.cm_tp.databases

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class SubjectRepository(private val subjectDao: SubjectDao) {

    init {

    }

    fun getAll() {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                subjectDao.getAll()
            }
    }

    fun getSubjectsByCourseAndYear(course: String, year: Int, callback: (subjectList: List<Subject>?) -> Unit) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                callback(subjectDao.getSubjectsByCourseAndYear(course, year))
            }
    }

    fun insertSubject(subject: Subject) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                subjectDao.insertSubject(subject)
            }
    }

    fun deleteByName(name: String, course: String) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                subjectDao.deleteByName(name, course)
            }
    }
}