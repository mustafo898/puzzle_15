package com.example.puzzle_15

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.SwitchCompat
import uz.kmax.a15puzzle2.utils.SharedPreferencesHelper
//import uz.micro.star.lesson_3_1.utils.SharedPreferencesHelper

class SettingsActivity : AppCompatActivity() {
    val shared by lazy {
        SharedPreferencesHelper(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val mode = findViewById<SwitchCompat>(R.id.mode)
        mode.isChecked = shared.getNightMode()
        mode.setOnClickListener {
            shared.setNightMode(mode.isChecked)
            if (mode.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}

