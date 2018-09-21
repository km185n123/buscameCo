package com.buscame.oncor.buscame.Historical.Rest

import com.buscame.oncor.buscame.Device.Rest.Response.DeviceAsociateResponse
import com.buscame.oncor.buscame.RestApi.ConstantsRestApi
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface EndPointHistoric {

    @Headers("Content-Type: application/json ")
    @POST(ConstantsRestApi.DEVICE_HISTORIC)
     fun historic(
            @Body deviceHistoricRequest: DeviceHistoricRequest,
            @Header("Authorization") authorization: String

    ): Call<DeviceHistoricResponse>
}