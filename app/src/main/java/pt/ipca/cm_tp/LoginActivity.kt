package pt.ipca.cm_tp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    fun performLogin(view: android.view.View) {
        val emailET = findViewById<EditText>(R.id.editText_email)
        val passwordET = findViewById<EditText>(R.id.editText_password)

        val email = emailET.text.toString()
        val psw = passwordET.text.toString()

        auth.signInWithEmailAndPassword(email, psw)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this,"Signed In", Toast.LENGTH_LONG).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Failed", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun performSignUp(view: android.view.View) {
        val emailET = findViewById<EditText>(R.id.editText_email)
        val passwordET = findViewById<EditText>(R.id.editText_password)

        val email = emailET.text.toString()
        val psw = passwordET.text.toString()

        auth.createUserWithEmailAndPassword(email, psw)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this,"Register Sucess", Toast.LENGTH_LONG).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Failed", Toast.LENGTH_LONG).show()
                }
            }
    }


}

