package com.buscame.oncor.buscame.SearchServiceCenter;

import android.content.Context;

import com.buscame.oncor.buscame.SearchServiceCenter.Rest.CityFindAllResponse;
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.ServiceCenterFindAllByRadiusAndCityRequest;
import com.buscame.oncor.buscame.SearchServiceCenter.Rest.ServiceCenterFindAllByRadiusAndCityResponse;

public interface SearchServiceCenterMVP {

    interface View{

        void loadCitys(CityFindAllResponse response);
        void loadServiceCenterPointsInMap(ServiceCenterFindAllByRadiusAndCityResponse response);
        void message(String message);
    }

    interface Presenter{


        void loadCitys(CityFindAllResponse response);
        void loadServiceCenterPointsInMap(ServiceCenterFindAllByRadiusAndCityResponse response);
        void citys(Context context,String accessToken);
        void serviceCenterPoints(ServiceCenterFindAllByRadiusAndCityRequest request, String accessToken);
        void message(String message);
    }

    interface Model{

       void citys(Context context,String accessToken);
       void serviceCenterPoints(ServiceCenterFindAllByRadiusAndCityRequest request, String accessToken);
    }
}
