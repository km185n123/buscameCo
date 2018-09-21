package com.buscame.oncor.buscame.Register.Model;

import com.buscame.oncor.buscame.RestApi.ConstantsRestApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EndPointRegister {


    @POST(ConstantsRestApi.CREATE_ACCOUNT)
    Call<AccountCreateResponse> createAcount(
            @Body AccountCreateRequest requestUser


    );


}
