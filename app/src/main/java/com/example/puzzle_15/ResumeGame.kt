//package uz.kmax.a15puzzle2
//
//import android.app.Dialog
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.Window
//import android.widget.RelativeLayout
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatDelegate
//import androidx.appcompat.widget.AppCompatButton
//import androidx.appcompat.widget.AppCompatImageView
//import androidx.appcompat.widget.SwitchCompat
//import com.example.puzzle_15.R
//import com.example.puzzle_15.Win_Activity
//import uz.kmax.a15puzzle2.utils.SharedPreferencesHelper
//import uz.micro.star.lesson_3_1.Coordinate
//import java.util.*
//import kotlin.collections.ArrayList
//import kotlin.concurrent.schedule
//import kotlin.math.absoluteValue
//
//class ResumeGame : AppCompatActivity() {
//    var allButtons = ArrayList<ArrayList<AppCompatButton>>()
//    var numbers = ArrayList<Int>()
//    val shared by lazy {
//        SharedPreferencesHelper(this)
//    }
//    private var scoreCounter = 0
//    var timeCounter = 0
//    lateinit var score: TextView
//    lateinit var time: TextView
//    lateinit var passiveCoordinate: Coordinate
//    lateinit var btnParent: RelativeLayout
//    val bundle by lazy {
//        Bundle()
//    }
//    val timer by lazy {
//        Timer()
//    }
//    val dialog by lazy{
//        Dialog(this)
//    }
//    lateinit var userName : TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.game)
//
//        btnParent = findViewById(R.id.btnParent)
//        loadAllViews()
//        loadNumbers()
//        userName = findViewById(R.id.name)
//        var userNAME = shared.getUserName()
//        userName.text = userNAME.toString()
//        restart()
//        dialogShow()
//        onClickButton()
//    }
//
//    private fun dialogShow() {
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
//        dialog.setContentView(R.layout.dialog)
//        val dialogCloseButton = dialog.findViewById<TextView>(R.id.dialogCloseButton)
//        dialogCloseButton.setOnClickListener {
//            dialog.dismiss()
//        }
//        // Night theme or Light theme switch this
//        val themeSwitch = dialog.findViewById<SwitchCompat>(R.id.Switch_theme_mode)
//        themeSwitch.isChecked = shared.getNightMode()
//        themeSwitch.setOnClickListener {
//            shared.setNightMode(themeSwitch.isChecked)
//            if (themeSwitch.isChecked){
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            }else{
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }
//        }
//        // theme code - End
//
//        val musicOff = dialog.findViewById<SwitchCompat>(R.id.musicOff)
//        musicOff.isChecked = shared.getMusicMode()
//        musicOff.setOnClickListener {
//            shared.setMusicMode(musicOff.isChecked)
//            if (musicOff.isChecked){
//
//            }else{
//
//            }
//        }
//    }
//
//    private fun onClickButton() {
//        val settingsButton = findViewById<AppCompatImageView>(R.id.settings_image)
//        settingsButton.setOnClickListener {
//            dialog.show()
//        }
//        var restartButton = findViewById<AppCompatButton>(R.id.restartButton)
//        restartButton.setOnClickListener {
//            val intent = Intent(this,GameActivity::class.java)
//            startActivity(intent)
//        }
//        val buttonBack = findViewById<AppCompatImageView>(R.id.buttonBack)
//        buttonBack.setOnClickListener {
//            finish()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    fun restart() {
//        loadDataToView()
//        setTimer()
//    }
//
//    fun loadAllViews() {
//        var list = ArrayList<AppCompatButton>()
//        for (i in 0 until btnParent.childCount) {
//            val b = btnParent.getChildAt(i)
//            b.setOnClickListener {
//                check(it as AppCompatButton)
//            }
//            b.tag = Coordinate(i / 4, i % 4)
//            list.add(b as AppCompatButton)
//            if ((i + 1) % 4 == 0) {
//                allButtons.add(list)
//                list = ArrayList()
//            }
//        }
//        /////
//
//        passiveCoordinate = Coordinate(shared.getX(), shared.getY())
//        //////
//        score = findViewById(R.id.score)
//        time = findViewById(R.id.time)
//    }
//
//    fun loadNumbers() {
//        numbers.addAll(shared.getAllNumbers())
//    }
//
//    fun loadDataToView() {
//        var t = 0
//        for (i in 0 until 4) {
//            for (j in 0 until 4) {
//                var t = numbers[t++]
//                if (t == -1) {
//                    allButtons[i][j].text = ""
//                    allButtons[i][j].setBackgroundResource(R.drawable.bg_passive_btn)
//                } else {
//                    allButtons[i][j].text = "$t"
//                    allButtons[i][j].setBackgroundResource(R.drawable.bg_active_btn)
//                }
//            }
//        }
//        scoreCounter = shared.getScoreCount()
//        timeCounter = shared.getTimerCount()
//        score.text = "$scoreCounter"
//        time.text = "$${timeFormat(timeCounter)}"
//    }
//
//    fun shuffle() {
//        numbers.shuffle()
//    }
//
//    fun setTimer() {
//        timer.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                time.text = "${timeFormat(++timeCounter)}"
//            }
//
//        }, 1000, 1000)
//    }
//
//    fun timeFormat(time: Int): String {
//        var minute = time / 60
//        var second = time % 60
//        var secondFormat = if (second < 10) "0${second}" else "$second"
//        var minuteFormat = if (minute < 10) "0${minute}" else "$minute"
//        return "${minuteFormat}:${secondFormat}"
//    }
//
//    fun check(activeBtn: AppCompatButton) {
//        val activeCoordinate = activeBtn.tag as Coordinate
//        if (
//            (activeCoordinate.x - passiveCoordinate.x).absoluteValue
//            + (activeCoordinate.y - passiveCoordinate.y).absoluteValue == 1
//        ) {
//            val passiveBtn = allButtons[passiveCoordinate.x][passiveCoordinate.y]
//            passiveBtn.text = activeBtn.text
//            activeBtn.text = ""
//            passiveBtn.setBackgroundResource(R.drawable.bg_active_btn)
//            activeBtn.setBackgroundResource(R.drawable.bg_passive_btn)
//            passiveCoordinate.x = activeCoordinate.x
//            passiveCoordinate.y = activeCoordinate.y
//
//            shared.setX(passiveCoordinate.x)
//            shared.setY(passiveCoordinate.y)
//            ////////////////////////////////////////
//            score.text = "${++scoreCounter}"
//            ///////////
//            if (isWin()) {
//                Toast.makeText(this, "You won ! ! !", Toast.LENGTH_SHORT).show()
//                timer.cancel()
//                Timer("SettingUp", false).schedule(2000) {
//                    val intent = Intent(this@ResumeGame, Win_Activity::class.java)
//                    intent.putExtra("SCORE", "$scoreCounter")
//                    intent.putExtras(bundle)
//                    startActivity(intent)
//                }
//            }
//        }
//    }
//
//    fun isWin(): Boolean {
//        if (passiveCoordinate.x != 3 && passiveCoordinate.y != 3) return false
//        var isTrue = true
//        for (i in 0..14) {
//            isTrue = isTrue && "${i + 1}" == allButtons[(i) / 4][(i) % 4].text.toString()
//        }
//        return isTrue
//    }
//
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
//        shared.setX(passiveCoordinate.x)
//        shared.setY(passiveCoordinate.y)
//        shared.setAllNumbers(numbers)
//        shared.setScoreCount(scoreCounter)
//        shared.setTimerCount(timeCounter)
//        super.onStop()
//    }
//}