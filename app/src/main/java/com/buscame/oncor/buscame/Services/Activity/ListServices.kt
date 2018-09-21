package com.buscame.oncor.buscame.Services.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.buscame.oncor.buscame.Profile.Model.Service
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.Services.Activity.Objects.ListServicesAdapter
import kotlinx.android.synthetic.main.activity_list_services.*

class ListServices : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_services)
        setTitle("Servicios")

       var services : HashSet<Service> = intent.extras.get("services") as HashSet<Service>



        val layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(this)
//
        listServicesRecyclerView.layoutManager = layoutManager
        listServicesRecyclerView.setHasFixedSize(true)
        val ServicesAdapter = ListServicesAdapter(this,services.toList())

        listServicesRecyclerView.adapter = ServicesAdapter





    }
}
