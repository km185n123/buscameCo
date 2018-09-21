package com.buscame.oncor.buscame.Device.Rest;

import com.buscame.oncor.buscame.Device.Rest.Request.CommandForGpsRequest;
import com.buscame.oncor.buscame.Device.Rest.Request.DeviceAsociateRequest;
import com.buscame.oncor.buscame.Device.Rest.Request.DeviceDissociateRequest;
import com.buscame.oncor.buscame.Device.Rest.Request.DeviceUpdateAliasAndImageRequest;
import com.buscame.oncor.buscame.Device.Rest.Response.CommandForGpsResponse;
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceAsociateResponse;
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceDissociateResponse;
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse;
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceUpdateAliasAndImageResponse;
import com.buscame.oncor.buscame.RestApi.ConstantsRestApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EndPointDevice {


    @Headers({
            "Content-Type: application/json ",

    })
    @POST(ConstantsRestApi.DEVICE_ASOCIATE)
    Call<DeviceAsociateResponse> asosiateDevice(
            @Body DeviceAsociateRequest deviceAsociateRequest,
            @Header("Authorization") String authorization

    );

    @POST(ConstantsRestApi.DEVICE_DISASSOCIATE)
    Call<DeviceDissociateResponse> disassociateDevice(
            @Body DeviceDissociateRequest deviceDissociateRequest,
            @Header("Authorization") String authorization

    );


    @POST(ConstantsRestApi.DEVICE_FIND_ALL)
    Call<DeviceFindAllByUserResponse> findAll(
            @Header("Authorization") String authorization

    );


    @POST(ConstantsRestApi.DEVICE_COMAND)
    Call<CommandForGpsResponse> command(
            @Body CommandForGpsRequest commandForGpsRequest,
            @Header("Authorization") String authorization

    );

    @POST(ConstantsRestApi.DEVICE)
    Call<DeviceUpdateAliasAndImageResponse> UpdateDataDevice(
            @Body DeviceUpdateAliasAndImageRequest deviceUpdateAliasAndImageRequest,
            @Header("Authorization") String authorization
    );

}
