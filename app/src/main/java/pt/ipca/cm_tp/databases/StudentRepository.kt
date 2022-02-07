package pt.ipca.cm_tp.databases

class StudentRepository(private val studentDao: StudentDao) {

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

    fun getAll() {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                studentDao.getAll()
            }
    }

    fun getNameById(id: Int): String {
        var studentName: String = "Error"
        AppDatabase
            .databaseWriteExecutor
            .execute {
                studentName = studentDao.getNameById(id)
            }

        return studentName
    }

    fun findStudentById(id: Int): Student {
        var student: Student = Student(0, "Error", "Error", "Error", 0)
        AppDatabase
            .databaseWriteExecutor
            .execute {
                student = studentDao.findStudentById(id)
            }

        return student
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