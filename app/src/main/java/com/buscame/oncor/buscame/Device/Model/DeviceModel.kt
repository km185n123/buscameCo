package com.buscame.oncor.buscame.Device.Model

import android.content.Context
import com.buscame.oncor.buscame.Device.Rest.EndPointDevice
import com.buscame.oncor.buscame.Device.Rest.Request.DeviceAsociateRequest
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceAsociateResponse
import com.buscame.oncor.buscame.RestApi.RestApiAdapter
import com.buscame.oncor.buscame.Device.DeviceMVP
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import io.reactivex.internal.subscriptions.SubscriptionHelper.isCancelled
import android.os.AsyncTask
import com.buscame.oncor.buscame.Device.Rest.Request.DeviceDissociateRequest
import com.buscame.oncor.buscame.Device.Rest.Request.DeviceUpdateAliasAndImageRequest
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceDissociateResponse
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceUpdateAliasAndImageResponse
import com.buscame.oncor.buscame.Util.DataCache
import com.google.gson.Gson


class DeviceModel : DeviceMVP.Model {
    private val restApiAdapter = RestApiAdapter()

    private val retrofit = restApiAdapter.conectionApiRest()

    var presenter: DeviceMVP.Presenter
    var dataCache:DeviceFindAllByUserResponse

    constructor(presenter: DeviceMVP.Presenter){

        this.presenter = presenter
        dataCache = DeviceFindAllByUserResponse()
    }
    override fun addDevice(codeDevice: String?,accessToken:String,context : Context?) {

        var deviceAsociateRequest = DeviceAsociateRequest()

        deviceAsociateRequest.code = codeDevice

        var response : Call<DeviceAsociateResponse> = retrofit
                .create(EndPointDevice::class.java)
                .asosiateDevice(deviceAsociateRequest,"Bearer "+accessToken)

        response.enqueue(object : Callback<DeviceAsociateResponse> {

            override fun onResponse(call: Call<DeviceAsociateResponse>?, response: Response<DeviceAsociateResponse>?) {

                if (response!!.code() == 200 && response.body() != null){

                    //presenter.goToDashBoard()
                    if (response.body()!!.code == 200){


                       // var db: AppDatabase = AppDatabase.getAppDatabase(context)
                       // db.DeviceDao().insert(response.body()!!.device)
                        FirebaseMessaging.getInstance().subscribeToTopic("code-1234")
                        presenter.newDevice(response.body()!!.device)



                        //presenter.
                    }else{
                        presenter.message("Codigo no valido")
                    }


                }else{
                    presenter.message("error")
                }
            }

            override fun onFailure(call: Call<DeviceAsociateResponse>?, t: Throwable?) {
                presenter.message("failure addDevice")
            }

        })


    }




    override fun updateDataDevice(device: Device?, accessToken: String?) {

       var DeviceUpdateAliasAndImageRequest =  DeviceUpdateAliasAndImageRequest()
        if (device != null) {
            DeviceUpdateAliasAndImageRequest.alias = device.alias
            DeviceUpdateAliasAndImageRequest.code = device.code
            DeviceUpdateAliasAndImageRequest.image = device.image
        }
        var response : Call<DeviceUpdateAliasAndImageResponse>? = retrofit
                .create(EndPointDevice::class.java)
                .UpdateDataDevice(DeviceUpdateAliasAndImageRequest,"Bearer "+accessToken)

        response!!.enqueue(object :Callback<DeviceUpdateAliasAndImageResponse>{

            override fun onResponse(call: Call<DeviceUpdateAliasAndImageResponse>?, response: Response<DeviceUpdateAliasAndImageResponse>?) {

                if (response!!.code() == 200){


                    presenter.message(response.message())
                    presenter.updateRenderDevice(device)

                }

            }

            override fun onFailure(call: Call<DeviceUpdateAliasAndImageResponse>?, t: Throwable?) {
                presenter.message("failure updateDataDevice")
            }

        })
    }




    override fun removeDevice(codeDevice: String?, position: Int, accessToken: String?) {




        FirebaseMessaging.getInstance().unsubscribeFromTopic("TOPIC_NAME")
         val deviceDissociateRequest =  DeviceDissociateRequest()
        deviceDissociateRequest.code = codeDevice

        var response: Call<DeviceDissociateResponse>? = retrofit
                .create(EndPointDevice::class.java)
                .disassociateDevice(deviceDissociateRequest, "Bearer "+accessToken)


        response!!.enqueue(object :Callback<DeviceDissociateResponse>{
            override fun onResponse(call: Call<DeviceDissociateResponse>?, response: Response<DeviceDissociateResponse>?) {

                if (response!!.code() == 200 && response.body() != null){

                    presenter.message(response.body()!!.message.toString())
                    presenter.removeDevice( position )
                }
            }

            override fun onFailure(call: Call<DeviceDissociateResponse>?, t: Throwable?) {

                if (t != null) {
                    presenter.message(t.message)
                }
            }

        })



    }





}