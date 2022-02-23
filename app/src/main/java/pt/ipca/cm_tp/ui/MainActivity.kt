package pt.ipca.cm_tp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.ipca.cm_tp.MyApplication
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.databases.Student
import pt.ipca.cm_tp.databases.StudentRepository
import pt.ipca.cm_tp.ui.fragments.*

class MainActivity : AppCompatActivity() {

    // Initialize fragments as lazy
    private val homeFragment by lazy { HomeFragment() }
    private val subjectFragment by lazy { SubjectsFragment() }
    private val deadlineFragment by lazy { DeadlinesFragment() }
    private val leaderboardFragment by lazy { LeaderboardFragment() }
    private val aboutFragment by lazy { AboutFragment() }
    lateinit var studentID: String
    lateinit var studentRepository: StudentRepository
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentID = intent.getStringExtra("studentID")!!

        // Get logged student info from firestore
        getStudentFromFirestore(studentID.toInt())

        // Load home fragment by default
        loadFragment(homeFragment)

        // Set on click listener on menu navigation to change fragment
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.mainNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                // Load home fragment
                R.id.home -> {
                    loadFragment(homeFragment)
                    true
                }

                // Load subjects fragment
                R.id.subject -> {
                    loadFragment(subjectFragment)
                    true
                }

                // Load schedule fragment
                R.id.deadline -> {
                    loadFragment(deadlineFragment)
                    true
                }

                // Load profile fragment
                R.id.leaderboard-> {
                    loadFragment(leaderboardFragment)
                    true
                }

                // Load about fragment
                R.id.about -> {
                    loadFragment(aboutFragment)
                    true
                }

                else -> false
            }
        }
    }

    /**
     * Loads fragments into ui
     * @param   Fragment    an activity fragment
     */
    fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navigationContainer, fragment)
            .commit()
    }

    private fun getStudentFromFirestore(id: Int) {
        // Initialize repositories
        studentRepository = (application as MyApplication).studentRepository

        // Get student info from firestore
        db.collection("students")
            .whereEqualTo("id", id)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("FIRESTORE_STUDENTS:", "${document.id} => ${document.data}")

                    // Insert student to local database
                    studentRepository.insertStudent(
                        Student(
                            id = document.get("id").toString().toInt(),
                            firstName = document.get("firstName") as String,
                            lastName = document.get("lastName") as String,
                            course = document.get("course") as String,
                            year = document.get("year").toString().toInt(),
                            email = document.get("email") as String,
                            phoneNumber = document.get("phoneNumber") as String,
                            address = document.get("address") as String,
                            status = document.get("status") as String,
                            averageGrade = document.get("averageGrade").toString().toDouble()
                        ))
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FIRESTORE_STUDENTS:", "Error getting documents: ", exception)
            }
    }
}