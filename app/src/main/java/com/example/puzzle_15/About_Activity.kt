package com.example.puzzle_15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.LinearLayoutCompat

class About_Activity : AppCompatActivity() {
//    lateinit var about:LinearLayoutCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about)
//        about = findViewById(R.id.about_id)
//        about = intent.getBundleExtra("BG") as LinearLayoutCompat
    }
}