package com.example.puzzle_15

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat

class Record_Activity : AppCompatActivity() {
    //    lateinit var about:LinearLayoutCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.records)

//    val score = intent.getIntExtra("SCORE",0)
//    findViewById<TextView>(R.id.res_step).text = " $score"
//
//    val time = intent.getIntExtra("TIME",0)
//    findViewById<TextView>(R.id.res_time).text = " ${timeFormat(time)}"

        val back = findViewById<AppCompatButton>(R.id.backButton)
        back.setOnClickListener {
            startActivity(Intent(this,Menu_Activity::class.java))
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