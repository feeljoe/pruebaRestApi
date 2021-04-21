package com.example.pruebarestapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pruebarestapi.model.AbstractAPIListener

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val emailField = findViewById<EditText>(R.id.emailText)
        val passwordField = findViewById<EditText>(R.id.passwordText)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            val model = Model.getInstance(this@LoginActivity.application)
            if (model != null) {
                model.login(email, password, object : AbstractAPIListener() {
                    override fun onLogin(user: User) {
                        model.user = user
                        Toast.makeText(this@LoginActivity, "User " + user.name + " has logged in!", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}