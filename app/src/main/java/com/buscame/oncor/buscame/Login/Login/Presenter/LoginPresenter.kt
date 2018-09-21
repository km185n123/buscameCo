package com.buscame.oncor.buscame.Login.Login.Presenter

import android.content.Context
import com.buscame.oncor.buscame.Device.Model.Device
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse
import com.buscame.oncor.buscame.Login.Login.Login
import com.buscame.oncor.buscame.Login.Login.Model.LoginModel
import com.buscame.oncor.buscame.Profile.Model.Account

class LoginPresenter : Login.Presenter {
    private  var view : Login.View

    private  var model : Login.Model

    constructor(view: Login.View) {
        this.view = view
        this.model = LoginModel(this)

    }

    override fun showMessage(result: String?) {

        if (view != null){
            view.showMessage(result)
        }

    }



    override fun loginUser(user: String?, password: String?, context: Context?) {

        if (view != null){
            model.loginUser(user,password,context)
        }
    }
    override fun goToFirstNewDevice(accessToken: String) {

        if (view != null){
            view.goToFirstNewDevice(accessToken)
        }

    }

    override fun goToDashBoard(accessToken: String?, response: DeviceFindAllByUserResponse?, account: Account?, refreshToken: String?) {

        if (view != null){
            view.goToDashBoard(accessToken,response,account,refreshToken)
        }

    }


    override fun goToLogin() {

        if (view != null){
            view.goToLogin()
        }
    }



    override fun closeSession(accesstoken: String?, context: Context?) {


        model.closeSession(accesstoken,context)

    }



}

