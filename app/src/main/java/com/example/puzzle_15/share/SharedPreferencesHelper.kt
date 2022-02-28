package uz.kmax.a15puzzle2.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Build
import java.util.*

class SharedPreferencesHelper(var context: Context) {

    private var preferences: SharedPreferences

    private lateinit var editor: SharedPreferences.Editor

    init {
        preferences = context.getSharedPreferences("APP_PREFS_NAME", MODE_PRIVATE)
    }

    /////////////////////////////
//    fun setTime(second: Int) {
//        editor = preferences.edit()
//        editor.putInt("SECOND", second)
//        editor.apply()
//    }
//
//    fun getSecond() = preferences.getInt("SECOND", 0)

    fun setNightMode(isNightMode: Boolean) {
        editor = preferences.edit()
        editor.putBoolean("IS_NIGHT", isNightMode)
        editor.apply()
    }

    fun getNightMode() = preferences.getBoolean("IS_NIGHT", false)

    fun clearTime() {
        preferences.edit().remove("SECOND").apply()
    }

    fun clearAllData() {
        preferences.edit().clear().apply()
    }

    fun setBackgroundMode(Unit: Int){
        editor = preferences.edit()
        editor.putInt("BACKGROUND_IMAGE", Unit)
        editor.apply()
    }

    fun getBackgroundMode() = preferences.getInt("BACKGROUND_IMAGE", 0)

    fun setBackgroundChange(isCanBeChanged: Boolean){
        editor = preferences.edit()
        editor.putBoolean("IS_CAN_BE_CHANGED", isCanBeChanged)
        editor.apply()
    }

    fun getBackgroundChange() = preferences.getBoolean("IS_CAN_BE_CHANGED",false)

    fun setMusicMode(Mode: Boolean){
        editor = preferences.edit()
        editor.putBoolean("MUSIC_MODE", Mode)
        editor.apply()
    }

    fun getMusicMode() = preferences.getBoolean("MUSIC_MODE",false)

    fun getLanguage() = preferences.getString("LANG", "ru")

    fun loadLocale(context: Context) {
        setLanguage(getLanguage()!!, context)
    }

    fun setLanguage(lang: String, context: Context) {
        editor = preferences.edit()
        editor.putString("LANG", lang)
        editor.apply()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, lang)
        }
        updateResourcesLegacy(context, lang)
    }

    private fun updateResources(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)//bu joyni uchirib kor
        return context.createConfigurationContext(configuration)
    }


    private fun updateResourcesLegacy(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }

    fun setAllNumbers(numbers: ArrayList<Int>) {
        editor = preferences.edit()

        numbers.forEachIndexed { index, number ->
            editor.putInt("NUMBER_${index}", number)
        }
        editor.apply()
    }

    fun setName(Name : String){
        editor = preferences.edit()
        editor.putString("NAME",Name)
        editor.apply()
    }

    fun getName() = preferences.getString("NAME","")

    fun getAllNumbers(): ArrayList<Int> {
        val numbers = ArrayList<Int>()
        var t = 1
        for (i in 0..16) {
            if (i == 16) {
                numbers.add(preferences.getInt("NUMBER_${i}", -1))
            } else {
                numbers.add(preferences.getInt("NUMBER_${i}", t++))
            }
        }
        return numbers
    }

    fun setX(CorX: Int){
        editor = preferences.edit()
        editor.putInt("COORDINATE_X", CorX)
        editor.apply()
    }

    fun getX() = preferences.getInt("COORDINATE_X",3)

    fun setY(CorY: Int){
        editor = preferences.edit()
        editor.putInt("COORDINATE_Y",CorY)
        editor.apply()
    }

    fun getY() = preferences.getInt("COORDINATE_Y",3)

    fun setTimerCount(second: Int){
        editor = preferences.edit()
        editor.putInt("TIME_COUNTER", second)
        editor.apply()
    }

    fun getTimerCount() = preferences.getInt("TIME_COUNTER", 0)

    fun setScoreCount(score: Int){
        editor = preferences.edit()
        editor.putInt("SCORE_COUNTER", score)
        editor.apply()
    }

    fun getScoreCount() = preferences.getInt("SCORE_COUNTER", 0)

    fun setResume(contune: Boolean){
        editor = preferences.edit()
        editor.putBoolean("RESUME_OR_NOT", contune)
        editor.apply()
    }

    fun getResume() = preferences.getBoolean("RESUME_OR_NOT",false)

    fun setUserName(UserName: String){
        editor = preferences.edit()
        editor.putString("USERNAME", UserName)
        editor.apply()
    }

    fun getUserName() = preferences.getString("USERNAME", "")

    fun setWinTime(Time: Int){
        editor = preferences.edit()
        editor.putInt("WIN_TIME", Time)
        editor.apply()
    }

    fun getWinTime() = preferences.getInt("WIN_TIME", 0)

    fun setWinScore(Score: Int){
        editor = preferences.edit()
        editor.putInt("WIN_SCORE", Score)
        editor.apply()
    }

    fun getWinScore() = preferences.getInt("WIN_SCORE", 0)
}