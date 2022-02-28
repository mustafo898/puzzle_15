package com.example.puzzle_15

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import uz.kmax.a15puzzle2.utils.SharedPreferencesHelper
import uz.micro.star.lesson_3_1.Coordinate
//import uz.micro.star.lesson_3_1.utils.SharedPreferencesHelper
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.math.absoluteValue

class Game_Activity : AppCompatActivity() {
    var allButtons = ArrayList<ArrayList<AppCompatButton>>()
    var numbers = ArrayList<Int>()
    private var scoreCounter = 0
    var timeCounter = 0
    lateinit var score: TextView
    lateinit var time: TextView

    val shared by lazy {
        SharedPreferencesHelper(this)
    }

    lateinit var passiveCoordinate: Coordinate
    lateinit var btnParent: RelativeLayout
    lateinit var timer: Timer
    lateinit var restart1: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        restart1 = findViewById(R.id.restart)
        btnParent = findViewById(R.id.btnParent)
        loadAllViews()
        loadNumbers()
//        shuffle()
        setTimer()
        loadDataToView()
        restart1.setOnClickListener {
            timer.cancel()
            loadAllViews()
//            shuffle()
            loadDataToView()
            setTimer()
        }

    }


    fun loadAllViews() {
        var list = ArrayList<AppCompatButton>()
        for (i in 0 until btnParent.childCount) {
            val b = btnParent.getChildAt(i)
            b.setOnClickListener {
                check(it as AppCompatButton)
            }
            b.tag = Coordinate(i / 4, i % 4)
            list.add(b as AppCompatButton)
            if ((i + 1) % 4 == 0) {
                print("ssss list ${list}")
                allButtons.add(list)
                list = ArrayList()
                print("ssss matrix $allButtons")
            }
        }
        /////
        passiveCoordinate = Coordinate(3, 3)
        //////
        score = findViewById(R.id.stepCount)
        time = findViewById(R.id.time)
    }

    fun loadNumbers() {
        for (i in 1..15) {
            numbers.add(i)
        }
    }
//zfngfjfjkfdjg
    fun loadDataToView() {
        var t = 0
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                if (i == 3 && j == 3) {
                    allButtons[i][j].text = ""
//                    allButtons[i][j].setBackgroundResource(R.drawable.bg_passive_btn)
                    allButtons[i][j].visibility = View.INVISIBLE

                } else {
                    allButtons[i][j].text = "${numbers[t++]}"
                    allButtons[i][j].setBackgroundResource(R.drawable.bg_active)
                    allButtons[i][j].visibility = View.VISIBLE
                }
            }
        }
        scoreCounter = 0
        timeCounter = 0
        score.text = "$scoreCounter"
        time.text = "${timeFormat(timeCounter)}"
    }

//    fun loadDataToView() {
//        var t = 0
//        for (i in 0 until 4) {
//            for (j in 0 until 4) {
//                var m = numbers[t++]
//                if (m == -1) {
//                    allButtons[i][j].text = ""
//                    allButtons[i][j].setBackgroundResource(R.drawable.bg_passive_btn)
//                    allButtons[i][j].visibility = View.INVISIBLE
//                } else {
//                    allButtons[i][j].text = "$m"
//                    allButtons[i][j].setBackgroundResource(R.drawable.bg_active)
//                    allButtons[i][j].visibility = View.VISIBLE
//                }
//            }
//        }
//    scoreCounter = 0
//    timeCounter = 0
//    score.text = "$scoreCounter"
//    time.text = "$${timeFormat(timeCounter)}"
//}

fun shuffle() {
    numbers.shuffle()
}

fun setTimer() {
    timer = Timer()
    timer.scheduleAtFixedRate(object : TimerTask() {
        override fun run() {
            time.text = "${timeFormat(++timeCounter)}"
        }
    }, 1000, 1000)
}

fun timeFormat(time: Int): String {
    var minute = time / 60
    var second = time % 60
    var secondFormat = if (second < 10) "0${second}" else "$second"
    var minuteFormat = if (minute < 10) "0${minute}" else "$minute"
    return "${minuteFormat}:${secondFormat}"
}

fun check(activeBtn: AppCompatButton) {
    val activeCoordinate = activeBtn.tag as Coordinate
    if (
        (activeCoordinate.x - passiveCoordinate.x).absoluteValue
        + (activeCoordinate.y - passiveCoordinate.y).absoluteValue == 1
    ) {
        val passiveBtn = allButtons[passiveCoordinate.x][passiveCoordinate.y]
        passiveBtn.text = activeBtn.text
        activeBtn.text = ""
        passiveBtn.setBackgroundResource(R.drawable.bg_active)
        activeBtn.setBackgroundResource(R.drawable.bg_passive_btn)
        passiveBtn.visibility = View.VISIBLE
        activeBtn.visibility = View.INVISIBLE
        passiveCoordinate.x = activeCoordinate.x
        passiveCoordinate.y = activeCoordinate.y
        ////////////////////////////////////////
        score.text = "${++scoreCounter}"
        ///////////
        if (isWin()) {
            Toast.makeText(this, "You won ! ! !", Toast.LENGTH_SHORT).show()
            timer.cancel()
            Timer("SettingUp", false).schedule(0) {
                val intent = Intent(this@Game_Activity, Win_Activity::class.java)
                intent.putExtra("SCORE", scoreCounter)
                intent.putExtra("TIME", timeCounter)
                startActivity(intent)
                scoreCounter = 0
                timeCounter = 0
            }
        }
    }
}

fun isWin(): Boolean {
    if (passiveCoordinate.x != 3 && passiveCoordinate.y != 3) return false
    var isTrue = true
    for (i in 0..14) {
        isTrue = isTrue && "${i + 1}" == allButtons[(i) / 4][(i) % 4].text.toString()
    }
    return isTrue
}

//    override fun onStop() {
//        val numbers = ArrayList<Int>()
//        for (i in 0 until btnParent.childCount) {
//            val d = (btnParent.getChildAt(i) as AppCompatButton).text.toString()
//            if (d.isEmpty()) {
//                numbers.add(-1)
//            } else {
//                numbers.add(d.toInt())
//            }
//        }
////        shared.setX(passiveCoordinate.x)
////        shared.setY(passiveCoordinate.y)
//        shared.setAllNumbers(numbers)
//        super.onStop()
//    }
}