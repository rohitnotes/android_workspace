package com.example.sec_19_snapchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    var emailEditText : EditText? = null
    var passwordEditText : EditText? = null
    private lateinit var auth: FirebaseAuth

    fun goClicked(view : View){
        // If we can login the user
        auth.signInWithEmailAndPassword(emailEditText?.text.toString(), passwordEditText?.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    logIn();
                    Toast.makeText(this, "Login task.isSuccessful", Toast.LENGTH_SHORT).show()
                } else {
                    auth.createUserWithEmailAndPassword(emailEditText?.text.toString(), passwordEditText?.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Add to Database
                                task.result?.user?.uid?.let {
                                    FirebaseDatabase.getInstance().getReference().child("users").child(
                                        it
                                    ).child("email").setValue(
                                        emailEditText?.text.toString())
                                }

                                logIn();
                            } else {
                                Toast.makeText(this, "Login Failed. Try Again", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
    }

    private fun logIn(){
        // Move to Next Activity
        val intent = Intent(this, SnapsActivity::class.java)
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = Firebase.auth

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEitText)

    }
}
