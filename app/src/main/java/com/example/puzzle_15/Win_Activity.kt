package com.example.puzzle_15

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import uz.kmax.a15puzzle2.utils.SharedPreferencesHelper

class Win_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val shared by lazy {
            SharedPreferencesHelper(this)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.win)

        val score = intent.getIntExtra("SCORE",0)
        findViewById<TextView>(R.id.step).text = " : $score"
        val time = intent.getIntExtra("TIME",0)
        findViewById<TextView>(R.id.time).text = " : ${timeFormat(time)}"

        var menu = findViewById<AppCompatButton>(R.id.menuButton)
        menu.setOnClickListener {
            startActivity(Intent(this@Win_Activity,First_Activity::class.java))
        }

        val name = findViewById<TextView>(R.id.name_win)
        name.text = shared.getName().toString()


//        val intent = Intent(this@Win_Activity, Record_Activity::class.java)
//        intent.putExtra("SCORE", score)
//        intent.putExtra("TIME", time)
//        startActivity(intent)


        var restart = findViewById<AppCompatButton>(R.id.restartButton)
        restart.setOnClickListener {
            startActivity(Intent(this@Win_Activity,Game_Activity::class.java))
        }
    }

    fun timeFormat(time: Int): String {
        var minute = time / 60
        var second = time % 60
        var secondFormat = if (second < 10) "0${second}" else "$second"
        var minuteFormat = if (minute < 10) "0${minute}" else "$minute"
        return "${minuteFormat}:${secondFormat}"
    }
}
