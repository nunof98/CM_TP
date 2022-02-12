package pt.ipca.cm_tp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.ui.fragments.*

class MainActivity : AppCompatActivity() {

    // Initialize fragments as lazy
    private val homeFragment by lazy { HomeFragment() }
    private val subjectsFragment by lazy { SubjectsFragment() }
    private val scheduleFragment by lazy { ScheduleFragment() }
    private val profileFragment by lazy { ProfileFragment() }
    private val aboutFragment by lazy { AboutFragment() }
    lateinit var studentID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentID = intent.getStringExtra("studentID")!!

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