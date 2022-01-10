package com.example.myapplicationregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    private lateinit var edittextemail: EditText
    private lateinit var edittextpassword: EditText
    private lateinit var repeatpassword: EditText
    private lateinit var signuobutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        init()
        signupListeners()
    }

    private fun init() {
        edittextemail = findViewById(R.id.email)
        edittextpassword = findViewById(R.id.password)
        repeatpassword = findViewById(R.id.repeatpassword)
        signuobutton = findViewById(R.id.signup)
    }

    private fun signupListeners() {
        signuobutton.setOnClickListener {
            val email = edittextemail.text.toString()
            val password = edittextpassword.text.toString()
            val repeatpassword = repeatpassword.text.toString()

            if (email.isEmpty() || password.isEmpty() || repeatpassword.isEmpty()) {
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (password.equals(repeatpassword)){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        task ->
                    if(task.isSuccessful){
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()


            }
                }


            }
            else if (!password.equals(repeatpassword)) {
                Toast.makeText(this, "passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener }

        }
    }
}