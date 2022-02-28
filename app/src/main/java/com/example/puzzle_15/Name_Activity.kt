package com.example.puzzle_15

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import uz.kmax.a15puzzle2.utils.SharedPreferencesHelper

class Name_Activity : AppCompatActivity() {
    val shared by lazy {
        SharedPreferencesHelper(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.name)

        val next = findViewById<AppCompatButton>(R.id.next)

        var name = findViewById<EditText>(R.id.name)

        next.setOnClickListener {
            if (name.length() < 3){
                Toast.makeText(this,"Does not match !",Toast.LENGTH_SHORT).show()
            }
            else{
                shared.setName(name.toString())
                Toast.makeText(this,"Success ! ! !",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,Game_Activity::class.java))
            }
        }
    }
}