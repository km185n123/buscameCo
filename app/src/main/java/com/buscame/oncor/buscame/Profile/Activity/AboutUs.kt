package com.buscame.oncor.buscame.Profile.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.buscame.oncor.buscame.R
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.buscame.oncor.buscame.Profile.Activity.Object.featuresAdadpter
import kotlinx.android.synthetic.main.activity_about_us.*


class AboutUs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        // data to populate the RecyclerView with
        val features = arrayListOf<String>()
        features.add("· Notificaciones en tiempo real ")
        features.add("· Safe parking")
        features.add("· Apagado seguro")


        // set up the RecyclerView

        val layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(this)
        recyclerFeatures.layoutManager = layoutManager
        recyclerFeatures.setHasFixedSize(true)
        recyclerFeatures.adapter = featuresAdadpter(features)

    }
}
