package com.buscame.oncor.buscame.Services.Presenter

import com.buscame.oncor.buscame.Device.Model.Device
import com.buscame.oncor.buscame.Services.Model.ServicesModel
import com.buscame.oncor.buscame.Services.Services

class ServicesPresenter:Services.ServicesType.Presenter {



    private var view: Services.ServicesType.View

    private var model: Services.ServicesType.Model
    constructor(view : Services.ServicesType.View){
        this.view = view
        this.model = ServicesModel(this)
    }

    override fun notification(messages: String?) {

        if (view != null){
            view.notification(messages)
        }

    }

    override fun notificationError(error: String?) {

        if (view != null){
            view.notificationError(error)
        }

    }



    override fun loadDevices(devices: MutableList<Device?>?) {

        if (view != null){
            view.loadDevices(devices)
        }

    }

    override fun listServices(accessToken: String?) {

        if (model != null){
          model.listServices(accessToken)
        }

    }
}