package com.example.mystory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivtiy : AppCompatActivity() {


    private var username: EditText? = null
    private var password: EditText? = null
    private var login: Button? = null
    private var checkBox: CheckBox? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activtiy)

        connectViews()
        login()
      //  checkFields()

    }

    private fun connectViews() {

        username = findViewById(R.id.username)
        password = findViewById(R.id.Password)
        login = findViewById(R.id.Login)
        checkBox = findViewById(R.id.checkbox)
    }

    private fun login() {
        val arr: ArrayList<User> = ArrayList()
        arr.add(User("ahmd@test.com", "333"))
        arr.add(User("t@test.com", "12345"))
        arr.add(User("test@test.com", "12345"))
        login?.setOnClickListener {
            val username = username?.text.toString()
            val password = password?.text.toString()
            val user = User(username, password)   // opject
            for (userArray in arr) {
                if (userArray.email == user.email
                    && userArray.password == user.password
                ) {
                    //   Toast.makeText(this, "Hi ${user.email}", Toast.LENGTH_SHORT).show()
                    finish()
                    val i = Intent(this, MainActivity::class.java)
                    i.putExtra("username ", userArray.email)
                    startActivity(i)
                    break
                } else {
                    Toast.makeText(this, "Make sure to write down the data", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }
    }

    private fun checkFields() {
        login?.setOnClickListener {
            if (username?.text?.isEmpty() == true) {
                username?.setError("here your email")
            } else if (password?.text?.isEmpty() == true) {
                password?.setError("here your password")

            }
        }
    }
}

