package pt.ipca.cm_tp.databases

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class StudentRepository(
    //db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val studentDao: StudentDao) {

    init {
    /*
        db.collection("students")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    //Log.d("FIRESTORE:", "${document.id} => ${document.data}")
                    val data = document.data
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FIRESTORE:", "Error getting documents: ", exception)
            }*/
        /*
        // Delete student
        AppDatabase
            .databaseWriteExecutor
            .execute {
                studentDao.deleteById(15464)
            }

        // Insert student
        AppDatabase
            .databaseWriteExecutor
            .execute {
                studentDao.insertStudent(
                    Student(15464,
                        "Nuno",
                        "Fernandes",
                        "Mestrado Engenharia Eletrónica e de Computadores",
                        1)
                )
            }
    */
    }

    fun getAll() {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                studentDao.getAll()
            }
    }

    fun getNameById(id: Int, callback: (name: String?) -> Unit) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                callback(studentDao.getNameById(id))
            }
    }

    fun findStudentById(id: Int, callback: (student: Student?) -> Unit) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                callback(studentDao.findStudentById(id))
            }
    }

    fun insertAll(vararg student: Student) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                studentDao.insertAll(*student)
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