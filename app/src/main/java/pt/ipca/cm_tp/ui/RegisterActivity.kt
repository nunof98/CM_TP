package pt.ipca.cm_tp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import pt.ipca.cm_tp.R
import pt.ipca.cm_tp.ui.fragments.CameraFragment
import pt.ipca.cm_tp.ui.fragments.NfcFragment

class RegisterActivity : AppCompatActivity() {

    // Initialize fragments as lazy
    private val nfcFragment by lazy { NfcFragment() }
    private val cameraFragment by lazy { CameraFragment() }

    lateinit var studentID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        studentID = intent.getStringExtra("studentID")!!


        // Load nfc fragment by default
        loadFragment(nfcFragment)

        // Set on click listener on menu navigation to change fragment
        val registerNavigationView = findViewById<BottomNavigationView>(R.id.registerNavigationView)
        registerNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                // Load nfc fragment
                R.id.nfc -> {
                    loadFragment(nfcFragment)
                    true
                }

                // Load camera fragment
                R.id.camera -> {
                    loadFragment(cameraFragment)
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
    fun loadFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navigationContainer, fragment)
            .commit()
    }
}