package com.buscame.oncor.buscame.Device.Presenter

import android.content.Context
import com.buscame.oncor.buscame.Device.DeviceMVP
import com.buscame.oncor.buscame.Device.Model.Device
import com.buscame.oncor.buscame.Device.Model.DeviceModel
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse

class DevicePresenter : DeviceMVP.Presenter {
    var view : DeviceMVP.View


    var model : DeviceMVP.Model


    constructor(view: DeviceMVP.View){

        this.view = view
        this.model = DeviceModel(this)
    }

    override fun message(message: String?) {

      if (view != null){
          view.message(message)
      }
    }

    override fun removeDevice(position: Int) {

        if (view != null){
            view.removeDevice(position)
        }

    }


    override fun newDevice(device: Device?) {

        if (view != null){
            view.newDevice(device)
        }

    }




    override fun updateRenderDevice(device: Device?) {

        if (view != null){
            view.updateRenderDevice(device)
        }
    }
    override fun updateDataDevice(device: Device?, accessToken: String?) {

        model.updateDataDevice(device,accessToken)
    }

    override fun addDevice(codeDevice: String?,accessToken: String,context: Context?) {
        model.addDevice(codeDevice,accessToken,context)
    }

    override fun removeDevice(codeDevice: String?, position: Int, accessToken: String?) {
        model.removeDevice(codeDevice,position,accessToken)
    }



}
