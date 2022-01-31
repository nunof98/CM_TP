package pt.ipca.cm_tp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pt.ipca.cm_room.MyDatabase
import pt.ipca.cm_tp.databases.Student
import pt.ipca.cm_tp.fragments.*

class MainActivity : AppCompatActivity() {

    // Initialize fragments as lazy
    private val homeFragment by lazy { HomeFragment() }
    private val subjectsFragment by lazy { SubjectsFragment() }
    private val scheduleFragment by lazy { ScheduleFragment() }
    private val profileFragment by lazy { ProfileFragment() }
    private val aboutFragment by lazy { AboutFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(applicationContext,
            MyDatabase::class.java, "student.db").build()

        /*
        GlobalScope.launch {
            db.studentDao().insertAll(
                Student(id = 15463, name = "Joaquin Dillen"),
                Student(id = 15464, name = "Nuno Fernandes"))
            val data = db.studentDao().getAll()

            data?.forEach {
                Log.d("ROOM:", it.id.toString() + ":" + it.name)
            }
        }
        */

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
                R.id.subjects -> {
                    loadFragment(subjectsFragment)
                    true
                }

                // Load schedule fragment
                R.id.schedule -> {
                    loadFragment(scheduleFragment)
                    true
                }

                // Load profile fragment
                R.id.profile -> {
                    loadFragment(profileFragment)
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
    private fun loadFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navigationContainer, fragment)
            .commit()
    }
}