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
import android.widget.TextView


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // change text on textView
        val signupTV = findViewById<TextView>(R.id.textView_signup_link)
        signupTV.text = Html.fromHtml("or <u><font color=#037B4A>Sign Up here</font></u>")

        // Initialize Firebase Auth
        auth = Firebase.auth

        emailET = findViewById<EditText>(R.id.editText_email)
        passwordET = findViewById<EditText>(R.id.editText_password)

        // autofill email and password
        emailET.setText(getSharedPreferences(this).getString("email", ""))
        passwordET.setText(getSharedPreferences(this).getString("password", ""))

        // get email and password from sharedPreferences
        val email = getSharedPreferences(this).getString("email", "") ?: ""
        val password = getSharedPreferences(this).getString("password", "") ?: ""

        // auto login if email and password are available
        if(email.isNotEmpty() && password.isNotEmpty()){
            // go to second activity after login
            //startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun performLogin(view: View) {
        val emailET = findViewById<EditText>(R.id.editText_email)
        val passwordET = findViewById<EditText>(R.id.editText_password)

        val email = emailET.text.toString()
        val password = passwordET.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // commit email and password to sharedPreferences
                    getSharedPreferences(this).edit().putString("email", email).apply()
                    getSharedPreferences(this).edit().putString("password", password).apply()
                    // get intent and start activity
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this,"Logged in", Toast.LENGTH_LONG).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Logged in failed", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun changeToSignUpActivity(view: android.view.View) {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
    }

}

