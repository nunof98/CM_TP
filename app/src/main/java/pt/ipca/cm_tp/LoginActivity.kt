package pt.ipca.cm_tp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.text.Html
import android.widget.CheckBox
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailTET: TextInputEditText
    private lateinit var passwordTET: TextInputEditText
    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Change text on textView
        val signupTV = findViewById<TextView>(R.id.textView_signup_link)
        signupTV.text = Html.fromHtml("or <u><font color=#037B4A>Sign Up here</font></u>")

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Get objects from login activity
        emailTET = findViewById<TextInputEditText>(R.id.tet_email)
        passwordTET = findViewById<TextInputEditText>(R.id.tet_password)
        checkBox = findViewById<CheckBox>(R.id.checkBox_login)

        // Autofill email and password and checkBox
        emailTET.setText(getSharedPreferences(this).getString("email", ""))
        passwordTET.setText(getSharedPreferences(this).getString("password", ""))
        checkBox.isChecked = getSharedPreferences(this).getBoolean("checkBox", false)

        // Get email and password from sharedPreferences
        val email = getSharedPreferences(this).getString("email", "") ?: ""
        val password = getSharedPreferences(this).getString("password", "") ?: ""

        // Auto login if email and password are available
        if(email.isNotEmpty() && password.isNotEmpty()){
            // go to second activity after login
            //changeToMainActivity()
        }
    }

    fun performLogin(view: View) {
        // Get objects from login activity
        val emailTET = findViewById<TextInputEditText>(R.id.tet_email)
        val passwordTET = findViewById<TextInputEditText>(R.id.tet_password)
        val checkBox = findViewById<CheckBox>(R.id.checkBox_login)

        // Get text from fields
        val email = emailTET.text.toString()
        val password = passwordTET.text.toString()
        val saveCredentials = checkBox.isChecked

        // Authenticate with Firebase
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Validate if checkBox is checked
                    if (saveCredentials)
                    {
                        // commit email and password to sharedPreferences
                        getSharedPreferences(this).edit().putString("email", email).apply()
                        getSharedPreferences(this).edit().putString("password", password).apply()
                    }
                    // commit checkBox state to sharedPreferences
                    getSharedPreferences(this).edit().putBoolean("checkBox", saveCredentials).apply()
                    // get intent and start activity
                    changeToMainActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    findViewById<TextView>(R.id.textView_error).visibility = View.VISIBLE
                }
            }
    }

    fun changeToSignUpActivity(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun changeToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
    }

}