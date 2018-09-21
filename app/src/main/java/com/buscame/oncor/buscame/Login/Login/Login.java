package com.buscame.oncor.buscame.Login.Login;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.buscame.oncor.buscame.Device.Model.Device;
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse;
import com.buscame.oncor.buscame.Profile.Model.Account;

import java.util.List;

public interface Login {



    interface View{



        void showMessage(String result);
        void goToDashBoard(String accessToken, DeviceFindAllByUserResponse response, Account account, String refreshToken);


         void goToLogin();


         void goToFirstNewDevice(String accessToken);


    }

    interface Presenter{


        void closeSession(String accesstoken,Context context);
        void goToDashBoard(String accessToken, DeviceFindAllByUserResponse response, Account account, String refreshToken);
        void goToLogin();
        void goToFirstNewDevice(String accessToken);
        void showMessage(String result);
        void loginUser(String user, String password,Context context);
    }

    interface Model{

        void closeSession(String accesstoken, Context context);
        void loginUser(String user, String password,Context context);
    }
}
