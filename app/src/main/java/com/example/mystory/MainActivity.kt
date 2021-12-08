package com.example.mystory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity : AppCompatActivity() {

    private var textViewEmail:TextView? =null
    private var drawerLayout: DrawerLayout? =null
    private var toolbarView:Toolbar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = intent.getStringExtra("email")

        connectViews()
        textViewEmail?.text = email
        setSupportActionBar(toolbarView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    private fun connectViews(){
        textViewEmail = findViewById(R.id.Email)
        drawerLayout = findViewById(R.id.drawer)
        toolbarView = findViewById(R.id.toolbar)

    }
}