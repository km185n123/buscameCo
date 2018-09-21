package com.buscame.oncor.buscame.Device.Activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.Util.DataCache
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.activity_new_device.*

class NewDevice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.activity_new_device)
        setTitle("")


        btnAsociate.setOnClickListener {
            if (!txtcode.text.isNullOrEmpty()){

                //presenter.addDevice(txtcode.text.toString(),accessToken,this)
               // setResult(Intent.OK)
                val returnIntent = Intent()
                returnIntent.putExtra("result_code", txtcode.text.toString())
                setResult(RESULT_OK, returnIntent)
                finish()


            }
        }

        animateBox()

        btnCancel.setOnClickListener {

            finish()
        }

    }

    fun animateBox(){

        YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(5)
                .playOn(findViewById(R.id.imgBox));

    }








}
