package com.buscame.oncor.buscame.Historical.Model

import com.buscame.oncor.buscame.Historical.HistoricalMVP
import com.buscame.oncor.buscame.Historical.Rest.DeviceHistoricRequest
import com.buscame.oncor.buscame.Historical.Rest.DeviceHistoricResponse
import com.buscame.oncor.buscame.Historical.Rest.EndPointHistoric
import com.buscame.oncor.buscame.RestApi.RestApiAdapter
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HistoricalModel:HistoricalMVP.Model {


    var presenter:HistoricalMVP.Presenter

    private val restApiAdapter = RestApiAdapter()


    private val retrofit = restApiAdapter.conectionApiRest()

    constructor(presenter:HistoricalMVP.Presenter){
        this.presenter = presenter
    }

    override fun historical(startDate: Date, endDate: Date, codeDevice: String, accessToken: String) {


        var deviceHistoricRequest = DeviceHistoricRequest()
        deviceHistoricRequest.code = codeDevice
        deviceHistoricRequest.startDate = startDate
        deviceHistoricRequest.endDate = endDate


        var response : Call<DeviceHistoricResponse> = retrofit
                .create(EndPointHistoric::class.java)
                .historic(deviceHistoricRequest,"Bearer "+accessToken)


        response.enqueue(object :Callback<DeviceHistoricResponse>{

            override fun onResponse(call: Call<DeviceHistoricResponse>?, response: Response<DeviceHistoricResponse>?) {

                if (response!!.code() == 200 && response.body() != null){

                    //presenter.goToDashBoard()
                    if (response.body()!!.code == 200 ){


                        presenter.loadHistoric(response.body()!!.historicForDay)



                        //presenter.
                    }else{
                        presenter.notification("No hay historicos disponibles")
                    }


                }else{
                    presenter.notificationError(response.body()?.message.toString()+"else")
                }
            }

            override fun onFailure(call: Call<DeviceHistoricResponse>?, t: Throwable?) {

                presenter.notificationError(t?.toString()+"failed")
            }

        })


    }


}