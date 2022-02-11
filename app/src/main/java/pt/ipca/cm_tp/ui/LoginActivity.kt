package pt.ipca.cm_tp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.text.Html
import android.util.Log
import android.widget.*
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import pt.ipca.cm_tp.R


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var textInputEmail: TextInputLayout
    private lateinit var textInputPassword: TextInputLayout
    private lateinit var checkBox: CheckBox
    lateinit var studentEmail: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Bind button press to login function
        findViewById<Button>(R.id.button_login).setOnClickListener {
            performLogin()
        }

        // Change text on textView
        val signupTV = findViewById<TextView>(R.id.textView_signup_link)
        signupTV.text = Html.fromHtml("or <u><font color=#037B4A>Sign Up here</font></u>")

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Get objects from login activity
        textInputEmail = findViewById(R.id.til_email)
        textInputPassword = findViewById(R.id.til_password)
        checkBox = findViewById(R.id.checkBox_login)

        // Autofill email and password and checkBox
        textInputEmail.editText?.setText(getSharedPreferences(this).getString("email", ""))
        textInputPassword.editText?.setText(getSharedPreferences(this).getString("password", ""))
        checkBox.isChecked = getSharedPreferences(this).getBoolean("checkBox", false)

        /*
        // Get email and password from sharedPreferences
        val email = getSharedPreferences(this).getString("email", "") ?: ""
        val password = getSharedPreferences(this).getString("password", "") ?: ""

        // Auto login if email and password are available
        if(email.isNotEmpty() && password.isNotEmpty()){
            // go to second activity after login
            performLogin()
        }
        */
    }

    /**
     * Performs Firebase login.
     * If successful it will change current activity to Main activity
     * If it isn't successful it will alert user trough error
     * messages, depending on the cause
     */
    private fun performLogin() {
        // Get text from fields
        val email = textInputEmail.editText?.text.toString()
        val password = textInputPassword.editText?.text.toString()
        val saveCredentials = checkBox.isChecked

        if (email.isNotEmpty()) {
            //Remove error message and layout space
            textInputEmail.error = null
            textInputEmail.isErrorEnabled = false

            if (password.isNotEmpty()) {
                //Remove error message and layout space
                textInputPassword.error = null
                textInputPassword.isErrorEnabled = false

                // Authenticate with Firebase
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            studentEmail = email
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
            } else {
                //Display error message
                textInputPassword.error = getString(R.string.error_enter_a_password)
            }
        } else {
            //Display error message
            textInputEmail.error = getString(R.string.error_enter_an_email)
        }
    }

    /**
     * Changes activity to Sign up activity
     * @param   View
     */
    fun changeToSignUpActivity(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    /**
     * Changes activity to Main activity
     */
    private fun changeToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Returns shared preferences object
     * @param   Context
     * @return  sharedPreferences object
     */
    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
    }

}