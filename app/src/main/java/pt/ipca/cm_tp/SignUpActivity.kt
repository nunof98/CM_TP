package pt.ipca.cm_tp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    fun performSignUp(view: View) {
        val firstNameET = findViewById<EditText>(R.id.editText_first_name)
        val lastNameET = findViewById<EditText>(R.id.editText_last_name)
        val emailET = findViewById<EditText>(R.id.editText_email)
        val passwordET = findViewById<EditText>(R.id.editText_password)

        // Get user information
        val firstName = firstNameET.text.toString()
        val lastName = lastNameET.text.toString()
        val email = emailET.text.toString()
        val password = passwordET.text.toString()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty())
        {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Get intent and start activity
                        startActivity(Intent(this, LoginActivity::class.java))
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this,"You are signed up", Toast.LENGTH_LONG).show()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this,"Sign up failed", Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            // If user doesn't fill out all fields
            Toast.makeText(this,"Fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    fun changeToLoginActivity(view: android.view.View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}