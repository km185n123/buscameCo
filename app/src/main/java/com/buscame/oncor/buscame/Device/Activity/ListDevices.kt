package com.buscame.oncor.buscame.Device.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.buscame.oncor.buscame.DashBoard.Activity.DashBoard
import com.buscame.oncor.buscame.Device.Model.Device
import com.buscame.oncor.buscame.R
import kotlinx.android.synthetic.main.activity_devices.*
import com.buscame.oncor.buscame.Device.Activity.Object.ListDevicesAdapter
import com.buscame.oncor.buscame.Device.DeviceMVP
import com.buscame.oncor.buscame.Device.Presenter.DevicePresenter
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse
import com.buscame.oncor.buscame.Login.Login.Activity.LoginActivity
import com.buscame.oncor.buscame.Login.Login.Login
import com.buscame.oncor.buscame.Login.Login.Presenter.LoginPresenter
import com.buscame.oncor.buscame.Profile.Model.Account
import com.buscame.oncor.buscame.Util.DataCache


class ListDevices : AppCompatActivity(), DeviceMVP.View , Login.View {
    var dataDevicesJson = ""

    val presenterDevice = DevicePresenter(DashBoard())

    val presenter = DevicePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)



        loadDevices(DataCache.getDevices(this).devices as ArrayList<Device>)
    }
    private fun loadDevices(devices: ArrayList<Device>) {

        if (devices != null){

            val layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(this) as RecyclerView.LayoutManager?

            listDeviceRecyclerView.layoutManager = layoutManager
            listDeviceRecyclerView.setHasFixedSize(true)

            val listDevicesAdapter = ListDevicesAdapter(this,devices,presenter)

            listDeviceRecyclerView.adapter = listDevicesAdapter
            listDevicesAdapter.notifyDataSetChanged()




        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        if (requestCode === 1) {
            if (resultCode === RESULT_OK) {

               loadDevices(DataCache.getDevices(this).devices as ArrayList<Device>)

            }
            if (resultCode === RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

    }

    override fun showMessage(result: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToDashBoard(accessToken: String?, response: DeviceFindAllByUserResponse?, account: Account?, refreshToken: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToLogin() {

        ActivityCompat.finishAffinity(this);

        var intent = Intent(this, LoginActivity::class.java)
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        ActivityCompat.finishAffinity(this);
        DataCache.writeData(this,"accessToken","")
        DataCache.clear(this)
        startActivity(intent)
    }

    override fun goToFirstNewDevice(accessToken: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun message(message: String?) {
       Toast.makeText(this,"Dispositivo  desasociado",Toast.LENGTH_SHORT).show()

    }

    var presenterLogin: LoginPresenter = LoginPresenter(this)
    override fun removeDevice(position: Int) {

        val data = DataCache.getDevices(this)
        data.devices.removeAt(position)
        DataCache.setDevices(data,this)
        if (data.devices.size > 0) {
            loadDevices(data.devices as ArrayList<Device>)
        }else{
          var token =  DataCache.readData(this,"accessToken")
           presenterLogin.closeSession(token,this)

        }

    }

    override fun newDevice(device: Device?) {

    }

    override fun updateRenderDevice(device: Device?) {

    }


}
