package com.buscame.oncor.buscame.SearchServiceCenter.Rest;

import com.buscame.oncor.buscame.Device.Rest.Request.DeviceAsociateRequest;
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceAsociateResponse;
import com.buscame.oncor.buscame.RestApi.ConstantsRestApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EndPointSearchServiceCenter {

    @Headers({
            "Content-Type: application/json ",

    })
    @POST(ConstantsRestApi.CITY_FINDALL)
    Call<CityFindAllResponse> citys(
            @Header("Authorization") String authorization

    );

    @POST(ConstantsRestApi.SERVICE_CENTER_FIND_ALL)
    Call<ServiceCenterFindAllByRadiusAndCityResponse> serviceCenterFindAll(
            @Body ServiceCenterFindAllByRadiusAndCityRequest serviceCenterFindAllByRadiusAndCityRequest,
            @Header("Authorization") String authorization

    );
}
