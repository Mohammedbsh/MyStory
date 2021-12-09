package com.example.mystory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private var textViewEmail: TextView? = null
    private var drawerLayout: DrawerLayout? = null
    private var toolbarView: Toolbar? = null
    private var navigationView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = intent.getStringExtra("email")

        connectViews()
        textViewEmail?.text = email
        setSupportActionBar(toolbarView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupDrawer()
        updateEmailnHeader(email!!)
        drawerClicks()

    }

    private fun updateEmailnHeader(email: String) {
        val headerView = navigationView?.getHeaderView(0)
        textViewEmail = headerView?.findViewById<TextView>(R.id.Email)
        textViewEmail?.text = email
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout?.openDrawer(GravityCompat.START)
                true
            }

            else -> true
        }
    }

    private fun connectViews() {
        textViewEmail = findViewById(R.id.Email)
        drawerLayout = findViewById(R.id.drawer)
        toolbarView = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navView)

    }

    private fun drawerClicks() {
        navigationView?.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home ->{
                    drawerLayout?.closeDrawer(GravityCompat.START)
                true

            }
            R.id.layout -> {
            finish()
            val i = Intent(this, LoginActivtiy::class.java)
            startActivity(i)

            true
        }
            else -> true

        }
    }
}
}


