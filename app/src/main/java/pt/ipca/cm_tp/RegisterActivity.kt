package pt.ipca.cm_tp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import pt.ipca.cm_tp.fragments.CameraFragment
import pt.ipca.cm_tp.fragments.NfcFragment

class RegisterActivity : AppCompatActivity() {

    private val nfcFragment by lazy { NfcFragment() }
    private val cameraFragment by lazy { CameraFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loadFragment(nfcFragment)

        val registerNavigationView = findViewById<BottomNavigationView>(R.id.registerNavigationView)
        registerNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nfc -> {
                    loadFragment(nfcFragment)
                    true
                }

                R.id.camera -> {
                    loadFragment(cameraFragment)
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