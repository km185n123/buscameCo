package com.buscame.oncor.buscame.RestApi

import com.buscame.oncor.buscame.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder


/**
 * Created by root on 24/11/17.
 */
class RestApiAdapter {


    fun conectionApiRest(): Retrofit {




        val gson = GsonBuilder()
                .setDateFormat("dd-MM-yyyy")
                .setLenient()
                .create()


        val retrofit:Retrofit = Retrofit.Builder()
               .baseUrl(ConstantsRestApi.ROOT_URL)
               .addConverterFactory(GsonConverterFactory.create(gson))
               .build()

        return retrofit

    }

}