package pt.ipca.cm_tp

import android.app.Application
import pt.ipca.cm_tp.databases.AppDatabase
import pt.ipca.cm_tp.databases.StudentRepository

class MyApplication : Application() {

    val database by lazy { AppDatabase.getDatabase(this) }
    val studentRepository by lazy { StudentRepository(database.studentDao()) }

}