package pt.ipca.cm_tp.databases

import androidx.lifecycle.LiveData

class StudentRepository(private val studentDao: StudentDao) {
    /*
    init {
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
    }
    */

    fun getAll() {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                studentDao.getAll()
            }
    }

    fun getNameById(id: Int, callback: (name: String?) -> Unit) {//}: String {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                callback(studentDao.getNameById(id))
            }
    }

    fun findStudentById(id: Int, callback: (student: Student?) -> Unit) { //: Student {
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