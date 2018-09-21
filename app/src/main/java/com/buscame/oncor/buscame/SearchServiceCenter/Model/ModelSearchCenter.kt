package com.buscame.oncor.buscame.SearchServiceCenter.Model

import android.content.Context
import android.util.Log
import com.buscame.oncor.buscame.RestApi.RestApiAdapter
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.CityFindAllResponse
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.EndPointSearchServiceCenter
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.ServiceCenterFindAllByRadiusAndCityRequest
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.ServiceCenterFindAllByRadiusAndCityResponse
import com.buscame.oncor.buscame.SearchServiceCenter.SearchServiceCenterMVP
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelSearchCenter:SearchServiceCenterMVP.Model {


    var restAdapter = RestApiAdapter()


    val retrofit = restAdapter.conectionApiRest()
    val presenter:SearchServiceCenterMVP.Presenter

    constructor( presenter: SearchServiceCenterMVP.Presenter) {

        this.presenter = presenter
    }

    override fun citys(context: Context?, accessToken: String?) {

        val response: Call<CityFindAllResponse> = retrofit
                .create(EndPointSearchServiceCenter::class.java)
                .citys("Bearer "+accessToken)


       response.enqueue(object : Callback<CityFindAllResponse> {

           override fun onResponse(call: Call<CityFindAllResponse>?, response: Response<CityFindAllResponse>?) {

               Log.e("response ciudades",response.toString())
               if (response!!.code() == 200 && response.body() != null){
                   presenter.loadCitys(response.body())
               }
           }

           override fun onFailure(call: Call<CityFindAllResponse>?, t: Throwable?) {

               if (t != null) {
                   Log.e("response faslla",t.message.toString())
               }
           }
       })


    }


    override fun serviceCenterPoints(serviceCenterFindAllByRadiusAndCityRequest: ServiceCenterFindAllByRadiusAndCityRequest?, accessToken: String?) {

        val response:Call<ServiceCenterFindAllByRadiusAndCityResponse> = retrofit
                .create(EndPointSearchServiceCenter::class.java)
                .serviceCenterFindAll(serviceCenterFindAllByRadiusAndCityRequest,"Bearer "+accessToken)

        response.enqueue(object : Callback<ServiceCenterFindAllByRadiusAndCityResponse>{

            override fun onResponse(call: Call<ServiceCenterFindAllByRadiusAndCityResponse>?, response: Response<ServiceCenterFindAllByRadiusAndCityResponse>?) {

                if (response!!.code() == 200 && response.body() != null){

                    if (response.body()!!.serviceCenters.size > 0){

                        presenter.loadServiceCenterPointsInMap(response.body())
                    }else{

                        presenter.message("No hay puntos cercanos en ese radio")
                    }


                }

            }

            override fun onFailure(call: Call<ServiceCenterFindAllByRadiusAndCityResponse>?, t: Throwable?) {
                if (t != null) {
                    Log.e("response faslla",t.message.toString())
                }
            }

        })

    }


}
