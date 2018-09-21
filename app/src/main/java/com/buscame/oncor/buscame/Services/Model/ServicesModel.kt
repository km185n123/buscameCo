package com.buscame.oncor.buscame.Services.Model

import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse
import com.buscame.oncor.buscame.Device.Rest.EndPointDevice
import com.buscame.oncor.buscame.RestApi.RestApiAdapter
import com.buscame.oncor.buscame.Services.Services
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesModel:Services.ServicesType.Model {

    private val restApiAdapter = RestApiAdapter()
    private val retrofit = restApiAdapter.conectionApiRest()

    private var presenter : Services.ServicesType.Presenter
    constructor(presenter : Services.ServicesType.Presenter){
        this.presenter = presenter
    }

    override fun listServices(accessToken : String) {

        val list: List<String> = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")

        var response : Call<DeviceFindAllByUserResponse> = retrofit
                .create(EndPointDevice::class.java)
                .findAll("Bearer "+accessToken)




        response.enqueue(object : Callback<DeviceFindAllByUserResponse> {

            override fun onResponse(call: Call<DeviceFindAllByUserResponse>?, response: Response<DeviceFindAllByUserResponse>?) {

                if (response!!.code() == 200 && response.body() != null){

                    //presenter.goToDashBoard()
                    if (response.body()!!.code == 200 && !response.body()!!.devices.isEmpty()){

                        var devices = response.body()!!.devices

                        presenter.loadDevices(devices)

                        presenter.notification("exitoso")

                    }else{
                        presenter.notificationError("Codigo no valido")
                    }


                }else{
                    presenter.notificationError("error")
                }
            }

            override fun onFailure(call: Call<DeviceFindAllByUserResponse>?, t: Throwable?) {
                presenter.notificationError("failure")
            }

        })



       // presenter.loadDevices(list)


    }
}