package com.buscame.oncor.buscame.DashBoard.Model

import com.buscame.oncor.buscame.DashBoard.DashBoard
import com.buscame.oncor.buscame.Device.Rest.Response.CommandForGpsResponse
import com.buscame.oncor.buscame.Device.Rest.EndPointDevice
import com.buscame.oncor.buscame.Device.Rest.Request.CommandForGpsRequest
import com.buscame.oncor.buscame.RestApi.RestApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  DashBoardModel:DashBoard.Model {


    private var presenter:DashBoard.Presenter
    private var restApiAdapter = RestApiAdapter()
    private var retrofit = restApiAdapter.conectionApiRest()

    constructor(presenter:DashBoard.Presenter){


        this.presenter = presenter

    }

    override fun commandDevice(commandId: Int, imei: String?, lat: Double, lng: Double, accessToken: String?) {

        var commandForGpsRequest = CommandForGpsRequest()
        commandForGpsRequest.commandId = commandId
        commandForGpsRequest.imei = imei
        commandForGpsRequest.lat = lat
        commandForGpsRequest.lng = lng

        var response: Call<CommandForGpsResponse> = retrofit
                .create(EndPointDevice::class.java)
                .command(commandForGpsRequest,"Bearer "+accessToken)

        response.enqueue(object : Callback<CommandForGpsResponse>{

            override fun onResponse(call: Call<CommandForGpsResponse>?, response: Response<CommandForGpsResponse>?) {

                if (response!!.code() == 200 && response.body() != null){

                    //presenter.goToDashBoard()
                    if (response.body()!!.code == 200){

                        presenter.notification("comando "+commandId)
                    }else{
                        presenter.notificationError("Codigo no valido")
                    }


                }else{
                    presenter.notificationError("error")
                }


            }

            override fun onFailure(call: Call<CommandForGpsResponse>?, t: Throwable?) {
                presenter.notificationError("error")

            }


        })
    }
}