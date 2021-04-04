package com.cronocode.intentrecyclerview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {
     var mFirebaseAnalytics: FirebaseAnalytics? = null
    var timerText: TextView? = null
    var stopStartButton: Button? = null
    var timer: Timer? = null
    var timerTask: TimerTask? = null
    var time = 0.0
    var timerStarted = true
    var pauseTime: Long = 0
    var resumeTime:kotlin.Long = 0
    var UserId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        timerText = findViewById<View>(R.id.timerText2) as TextView
        timer = Timer()
        startTimer()
       btn_dress.setOnClickListener {
//           a("timerText2.text.toString()")
           Toast.makeText(this, "message" + timerText2.text.toString(), Toast.LENGTH_SHORT).show()

           var intent= Intent(this, MainActivity::class.java)
           startActivity(intent)
       }

        btn_blouse.setOnClickListener {

            var intent= Intent(this, MainActivity3::class.java)
            startActivity(intent)

        }

         btn_jacket.setOnClickListener {

             var intent= Intent(this, MainActivity3::class.java)
             startActivity(intent)
        }

    }

    fun a(a: String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "MainActivity")
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "Mai2nActivity")
      mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)

        pauseTime = System.currentTimeMillis()

        val bundlelog = Bundle()
        bundlelog.putString(
            "timeTracker", "User id : $UserId Main  page" + TimeUnit.SECONDS.convert(
                pauseTime - resumeTime, TimeUnit.MILLISECONDS
            ).toString() + " Seconds"
        )
        Log.d(
            "aaaa",
            TimeUnit.SECONDS.convert(pauseTime - resumeTime, TimeUnit.MILLISECONDS)
                .toString() + " Seconds"
        )
        mFirebaseAnalytics!!.setUserProperty(
            "timeTracker", "User id : " + UserId + "Main" + " page" + TimeUnit.SECONDS.convert(
                pauseTime - resumeTime, TimeUnit.MILLISECONDS
            ).toString() + " Seconds"
        )
        mFirebaseAnalytics!!.logEvent("timeTracker", bundlelog)
        Log.d("aaaa", "Tracking is done..")

    }

    fun startStopTapped(view: View?) {
        timerStarted = false
        timerTask!!.cancel()
    }

    private fun startTimer() {
        timerTask = object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    time++
                    timerText!!.text = getTimerText()
                }
            }
        }
        timer!!.scheduleAtFixedRate(timerTask, 0, 1000)
    }

    private fun getTimerText(): String {
        val rounded = Math.round(time).toInt()
        val seconds = rounded % 86400 % 3600 % 60
        val minutes = rounded % 86400 % 3600 / 60
        val hours = rounded % 86400 / 3600
        return formatTime(seconds, minutes, hours)
    }

    private fun formatTime(seconds: Int, minutes: Int, hours: Int): String {
        return String.format("%02d", hours) + " : " + String.format(
            "%02d",
            minutes
        ) + " : " + String.format("%02d", seconds)
    }
}

