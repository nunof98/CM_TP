package pt.ipca.cm_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ipca.cm_tp.databases.Student
import pt.ipca.cm_tp.databases.StudentDao

@Database(entities = [Student::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile private var instance: MyDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            MyDatabase::class.java, "student.db").build()
    }
}