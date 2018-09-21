package com.buscame.oncor.buscame.Services.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.Services.Activity.Objects.TypeServicesAdapter
import com.buscame.oncor.buscame.Services.Presenter.ServicesPresenter
import com.buscame.oncor.buscame.Services.Services
import com.buscame.oncor.buscame.Util.DataCache
import kotlinx.android.synthetic.main.activity_type_services.*
import com.buscame.oncor.buscame.Device.Model.Device
import com.buscame.oncor.buscame.Profile.Model.ServiceType
import java.util.*
import android.widget.ArrayAdapter
import kotlin.collections.ArrayList


class TypeServices : AppCompatActivity(), Services.ServicesType.View {


    var presenter = ServicesPresenter(this)

    var SPINNERLIST = arrayListOf("")
    var devices: MutableList<Device>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_type_services)
        var accessToken =   DataCache.readData(this,"accessToken")
        presenter.listServices(accessToken)

        loadDeviceSpinner(SPINNERLIST)


    }

    private fun loadDeviceSpinner(spinnerlist: ArrayList<String>) {

        val arrayAdapter = ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, spinnerlist)
        spinerDevice.setAdapter(arrayAdapter)

        spinerDevice.setOnItemClickListener { parent, view, position, id ->  loadTypeServices(this.devices ,id.toInt())   }
    }


    private fun loadTypeServices(devices: MutableList<Device>?, position: Int) {

        if (devices != null){

            var ServiceTypes =  devices!!.get(position).category!!.serviceTypes.toList()

            val layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(this) as RecyclerView.LayoutManager?
//
            typeServicesRecyclerView.layoutManager = layoutManager
            typeServicesRecyclerView.setHasFixedSize(true)
            val typeServicesAdapter = TypeServicesAdapter(this,ServiceTypes)

            typeServicesRecyclerView.adapter = typeServicesAdapter


        }
    }




    override fun loadDevices(devices: MutableList<Device>?) {

        this.devices = devices
        loadTypeServices(this.devices,0)

        SPINNERLIST.clear()

        devices!!.forEach { device : Device -> SPINNERLIST.add(device.name!!) }

        if (SPINNERLIST.size > 1){
            spinerDevice.visibility = View.VISIBLE

            loadDeviceSpinner(SPINNERLIST)
        }


    }


    override fun notification(messages: String?) {
        Toast.makeText(this,messages,Toast.LENGTH_LONG).show()
    }

    override fun notificationError(error: String?) {

        Toast.makeText(this,error,Toast.LENGTH_LONG).show()
    }


    private fun sortList(answersList: List<ServiceType>) {
        Collections.sort(answersList, object : Comparator<ServiceType> {
            override fun compare(s1: ServiceType, s2: ServiceType): Int {
                return (s1.name as String).compareTo(s2.name)
            }
        })
    }



}
