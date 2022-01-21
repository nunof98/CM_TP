package pt.ipca.cm_tp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
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

        findViewById<Button>(R.id.button_sign_up).setOnClickListener {
            performSignUp()
        }


    }

    fun performSignUp() {
        val nameTET = findViewById<TextInputEditText>(R.id.tet_name)
        val emailTET = findViewById<TextInputEditText>(R.id.tet_email)
        val passwordTET = findViewById<TextInputEditText>(R.id.tet_password)

        // Get user information
        val name = nameTET.text.toString()
        val email = emailTET.text.toString()
        val password = passwordTET.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty())
        {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Get intent and start activity
                        changeToLoginActivity()
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this,"You are signed up", Toast.LENGTH_LONG).show()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this,"Sign up failed", Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            // If user doesn't fill out all fields
            findViewById<TextView>(R.id.textView_error).visibility = View.VISIBLE
        }
    }

    fun changeToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}