package com.buscame.oncor.buscame.Register.Activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.buscame.oncor.buscame.DashBoard.Activity.DashBoard
import com.buscame.oncor.buscame.R

class Slider : AppCompatActivity() {

    var isFirstStart: Boolean = false
    internal var mcontext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)


        val t = Thread(Runnable {
            //  Intro App Initialize SharedPreferences
            val getSharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(baseContext)

            //  Create a new boolean and preference and set it to true
            isFirstStart = getSharedPreferences.getBoolean("firstStart", true)

            //  Check either activity or app is open very first time or not and do action
            if (isFirstStart) {

                //  Launch application introduction screen
                val i = Intent(this, SliderIntro::class.java)
                startActivity(i)
                val e = getSharedPreferences.edit()
                e.putBoolean("firstStart", false)
                e.apply()
            }
        })
        t.start()

        val i = Intent(this, DashBoard::class.java)
        startActivity(i)
        finish()

    }
}
