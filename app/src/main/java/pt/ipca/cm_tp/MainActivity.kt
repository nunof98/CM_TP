package pt.ipca.cm_tp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import pt.ipca.cm_tp.fragments.*

class MainActivity : AppCompatActivity() {

    private val homeFragment by lazy { HomeFragment() }
    private val subjectsFragment by lazy { SubjectsFragment() }
    private val scheduleFragment by lazy { ScheduleFragment() }
    private val profileFragment by lazy { ProfileFragment() }
    private val aboutFragment by lazy { AboutFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(homeFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.mainNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    loadFragment(homeFragment)
                    true
                }

                R.id.subjects -> {
                    loadFragment(subjectsFragment)
                    true
                }

                R.id.schedule -> {
                    loadFragment(scheduleFragment)
                    true
                }

                R.id.profile -> {
                    loadFragment(profileFragment)
                    true
                }

                R.id.about -> {
                    loadFragment(aboutFragment)
                    true
                }

                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navigationContainer, fragment)
            .commit()
    }
}