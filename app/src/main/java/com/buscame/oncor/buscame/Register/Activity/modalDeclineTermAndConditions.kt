package com.buscame.oncor.buscame.Register.Activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.buscame.oncor.buscame.R
import kotlinx.android.synthetic.main.activity_decline_term_and_conditions.*

class modalDeclineTermAndConditions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decline_term_and_conditions)


        btnCancel_decline.setOnClickListener {
            finish()
        }

        btnCallCenter.setOnClickListener {
            val phone = "+34666777888"
             val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }
    }
}
