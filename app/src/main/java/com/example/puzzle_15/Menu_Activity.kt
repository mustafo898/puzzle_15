package com.example.puzzle_15

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatButton
import kotlin.system.exitProcess

class Menu_Activity : AppCompatActivity() {
    lateinit var play:AppCompatButton
    lateinit var result:AppCompatButton
    lateinit var exit:AppCompatButton
    lateinit var resume:AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        play = findViewById(R.id.play_res)
        result = findViewById(R.id.result_res)
        exit = findViewById(R.id.exit_res)
        resume = findViewById(R.id.resume_res)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.title = "15 Puzzle"
        setSupportActionBar(toolbar)

        play.setOnClickListener {
            startActivity(Intent(this,Name_Activity::class.java))
        }

        result.setOnClickListener {
            startActivity(Intent(this,Record_Activity::class.java))
        }

        resume.setOnClickListener {
            startActivity(Intent(this,Game_Activity::class.java))
        }

        exit.setOnClickListener {
            onDestroy()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.about -> {
                val intent = Intent(this, About_Activity::class.java)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}