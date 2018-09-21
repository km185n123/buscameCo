package com.buscame.oncor.buscame.Device.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.buscame.oncor.buscame.DashBoard.Activity.DashBoard
import com.buscame.oncor.buscame.Device.DeviceMVP
import com.buscame.oncor.buscame.Device.Model.Device
import com.buscame.oncor.buscame.Device.Presenter.DevicePresenter
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.Util.DataCache
import kotlinx.android.synthetic.main.activity_new_device.*

class FirstNewDevice : AppCompatActivity() , DeviceMVP.View{

    var presenter = DevicePresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_new_device)

        btnAsociate.setOnClickListener { attempAssociateNewDevice() }
    }

    /**
     *
     * This method validate that field code is not null
     * also validate the session is active
     */
    private fun attempAssociateNewDevice() {

        if (!txtcode.text.isNullOrEmpty()){
            var accessToken =   DataCache.readData(this,"accessToken")
            presenter.addDevice(txtcode.text.toString(),accessToken,this)
        }
    }

    /**
     *
     * Print a response message of server
     */
    override fun message(message: String?) {

        Toast.makeText(this,message, Toast.LENGTH_LONG).show()

    }


    /**
     *
     * This method save new device in the preferences and it  go to the dashboard
     */
    override fun newDevice(device: Device?) {

        var intent = Intent(this, DashBoard::class.java)
      //  intent.putExtra("DEVICES", hashSetOf(device).toHashSet())
        DataCache.addDevice(this,device)
        startActivity(intent)
        finish()

    }


    /**
     *
     * This method are not used here
     */
    override fun updateRenderDevice(device: Device?) {}
    override fun removeDevice(position: Int) {}
}
