package pt.ipca.cm_tp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import pt.ipca.cm_tp.fragments.AboutFragment
import pt.ipca.cm_tp.fragments.CalendarFragment
import pt.ipca.cm_tp.fragments.HomeFragment
import pt.ipca.cm_tp.fragments.SubjectsFragment

class MainActivity : AppCompatActivity() {

    private val homeFragment by lazy { HomeFragment() }
    private val subjectsFragment by lazy { SubjectsFragment() }
    private val calendarFragment by lazy { CalendarFragment() }
    private val aboutFragment by lazy { AboutFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(homeFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
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

                R.id.calendar -> {
                    loadFragment(calendarFragment)
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