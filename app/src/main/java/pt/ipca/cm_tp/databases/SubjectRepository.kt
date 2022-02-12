package pt.ipca.cm_tp.databases

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

    fun getCourseSubjects(course: String, callback: (subjectList: List<Subject>?) -> Unit) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                callback(subjectDao.getCourseSubjects(course))
            }
    }

    fun insertAll(vararg subject: Subject) {
        AppDatabase
            .databaseWriteExecutor
            .execute {
                subjectDao.insertAll(subject)
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