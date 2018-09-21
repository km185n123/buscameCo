package com.buscame.oncor.buscame.Login.Login.Model;

import com.buscame.oncor.buscame.RestApi.ConstantsRestApi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface EndPointAuth {


    @FormUrlEncoded
    @POST(ConstantsRestApi.AUTHENTICATION)
    Call<Authorization> login(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password
    );


    @POST(ConstantsRestApi.LOGOUT)
    Call<OauthLogoutResponse> logout(
            @Header("Authorization") String authorization
    );

    @FormUrlEncoded
    @POST(ConstantsRestApi.LOGOUT)
    Call<Authorization> refresh_token(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType,
            @Field("refresh_token") String username
    );



}
