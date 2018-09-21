package com.buscame.oncor.buscame.Register.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.buscame.oncor.buscame.R
import kotlinx.android.synthetic.main.activity_terms_and_conditions.*


class termsAndConditions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)
        setTitle("")


        btnAccep.setOnClickListener {

            var goToFormCreateUser = Intent(this, FormCreateNewUser::class.java)
            startActivity(goToFormCreateUser)
        }

        btnDecline.setOnClickListener {

            var goToDeclineTerms = Intent(this, modalDeclineTermAndConditions::class.java)
            startActivity(goToDeclineTerms)
        }


    }
}
