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
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private var textViewEmail: TextView? = null
    private var drawerLayout: DrawerLayout? = null
    private var toolbarView: Toolbar? = null
    private var navigationView: NavigationView? = null
    private var recyclerView: RecyclerView? = null  //worpContent
    private var buttonAddStory:FloatingActionButton?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = intent.getStringExtra("email")

        connectViews()

        textViewEmail?.text = email
        setSupportActionBar(toolbarView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupDrawer()
        try {
            updateEmailInHeader(email!!)

        }catch (e:NullPointerException){

        }

        drawerClicks()
        openAddStoryActivity()
        displayStories()

    }

    private fun updateEmailInHeader(email:String) {
        val headerView = navigationView?.getHeaderView(0)
        val textViewEmail = headerView?.findViewById<TextView>(R.id.tvEmail)
        textViewEmail?.text = email
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // تغيير العلامه الى خطوط
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout?.openDrawer(GravityCompat.START)
                true
            }

            else -> true
        }
    }

    private fun connectViews() {
        textViewEmail = findViewById(R.id.tvEmail)
        drawerLayout = findViewById(R.id.drawer)
        toolbarView = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navView)
        recyclerView = findViewById(R.id.storiesRecyclerView)
        buttonAddStory = findViewById(R.id.btnAddStory)

    }

    private fun drawerClicks(){
        navigationView?.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    drawerLayout?.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.logout -> {
                    finish()
                    val i = Intent(this,LoginActivity::class.java)
                    startActivity(i)

                    true
                }
                else -> true

            }
        }
    }
    private fun openAddStoryActivity(){
        buttonAddStory?.setOnClickListener {
            val i = Intent(this,AddStoryActivity::class.java)
            startActivity(i)
        }
    }


    private fun displayStories(){  // عرض القصص
        val storiesArray = ArrayList<Story>()
        storiesArray.add(
            Story("This is my first Story i will show","subtitle","i am learn and It is a very basic calculator where you enter numbers using the apps buttons. It performs the calculation when any of the operation keys are pressed (plus, minus, multiple and divide). It works both in landscape and in portrait view"))
storiesArray.add(
            Story("my second Story i will show","subtitle","i am learn"))
storiesArray.add(
            Story("This is my third Story i will show","subtitle","i am learn"))



        val customAdapter = CustomAdapter(storiesArray,this)
        recyclerView?.adapter = customAdapter

    }


}


