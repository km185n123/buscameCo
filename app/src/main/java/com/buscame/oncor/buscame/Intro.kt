package com.buscame.oncor.buscame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.buscame.oncor.buscame.DashBoard.Activity.DashBoard
import com.buscame.oncor.buscame.Login.Login.Activity.LoginActivity
import com.buscame.oncor.buscame.Util.DataCache


class Intro : AppCompatActivity() {

    private val interval = 2000 // 2 segundos
    private val handler = Handler()

    private val runnable = Runnable {

        // the welcome layout is called after 2 seconds
        validateSession()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        // start intro timer
        handler.postAtTime(runnable, System.currentTimeMillis()+interval)
        handler.postDelayed(runnable, interval.toLong())
    }

    /**
     *
     * This method verify that the session is active
     * will validate if the access token be find in the preferences
     */
    private fun validateSession() {

        var intent = Intent()

        var accessToken =   DataCache.readData(this,"accessToken")
        var data =   DataCache.getDevices(this)

        if(accessToken.equals("") || accessToken == null || data == null){

            // if session is not in preferences  will be redirect login
            intent = Intent(this, LoginActivity::class.java)

        }else{
            // otherwise it will be redirected to the dashboard
            intent = Intent(this, DashBoard::class.java)

        }

        startActivity(intent)
        finish()

    }



}
