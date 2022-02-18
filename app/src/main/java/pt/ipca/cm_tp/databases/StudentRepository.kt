package pt.ipca.cm_tp.databases

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class StudentRepository(private val studentDao: StudentDao) {

    init {

    }

    fun getAll(callback: (studentList: List<Student>?) -> Unit) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                callback(studentDao.getAll())
            }
    }

    fun getNameById(id: Int, callback: (name: String?) -> Unit) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                callback(studentDao.getNameById(id))
            }
    }

    fun getCourseById(id: Int, callback: (course: String?) -> Unit) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                callback(studentDao.getCourseById(id))
            }
    }

    fun findStudentById(id: Int, callback: (student: Student?) -> Unit) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                callback(studentDao.findStudentById(id))
            }
    }

    fun insertStudent(student: Student) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                studentDao.insertStudent(student)
            }
    }

    fun deleteById(id: Int) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                studentDao.deleteById(id)
            }
    }
}