package com.buscame.oncor.buscame.SearchServiceCenter.Presenter

import android.content.Context
import com.buscame.oncor.buscame.SearchServiceCenter.Model.ModelSearchCenter
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.CityFindAllResponse
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.ServiceCenterFindAllByRadiusAndCityRequest
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.ServiceCenterFindAllByRadiusAndCityResponse
import com.buscame.oncor.buscame.SearchServiceCenter.SearchServiceCenterMVP

class presenterSearchService:SearchServiceCenterMVP.Presenter {


    val view:SearchServiceCenterMVP.View

    val model:SearchServiceCenterMVP.Model
    constructor(view: SearchServiceCenterMVP.View) {
        this.view = view
        model = ModelSearchCenter(this)
    }

    override fun loadCitys(response: CityFindAllResponse?) {

        if (view != null){

            view.loadCitys(response)
        }
    }

    override fun message(message: String?) {
        if (view != null){

            view.message(message)
        }

    }


    override fun citys(context: Context?, accessToken: String?) {

        model.citys(context,accessToken)
    }

    override fun serviceCenterPoints(request: ServiceCenterFindAllByRadiusAndCityRequest?, accessToken: String?) {

        model.serviceCenterPoints(request,accessToken)
    }

    override fun loadServiceCenterPointsInMap(response: ServiceCenterFindAllByRadiusAndCityResponse?) {

        if (view != null){
            view.loadServiceCenterPointsInMap(response)
        }
    }


}