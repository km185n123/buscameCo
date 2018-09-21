package com.buscame.oncor.buscame.DashBoard.Presenter

import com.buscame.oncor.buscame.DashBoard.DashBoard
import com.buscame.oncor.buscame.DashBoard.Model.DashBoardModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

class DashBoardPresenter:DashBoard.Presenter {
    lateinit var view : DashBoard.View

    lateinit var model : DashBoard.Model


    constructor(view:DashBoard.View){

        this.view = view
        this.model = DashBoardModel(this)
    }
    override fun notification(message: String?) {
        if (view != null){
            view.notification(message)
        }

    }

    override fun notificationError(error: String?) {
        if (view != null){
           view.notificationError(error)
        }

    }


    override fun commandDevice(commandId: Int, imei: String?, lat: Double, lng: Double, accessToken: String?) {

        if (view != null){
            model.commandDevice(commandId,imei,lat,lng,accessToken)
        }

    }


}