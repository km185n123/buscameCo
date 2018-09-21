package com.buscame.oncor.buscame.Register.Presenter

import android.util.Log
import com.buscame.oncor.buscame.Register.Model.RegisterModel
import com.buscame.oncor.buscame.Register.Register

class RegisterPresenter : Register.Presenter {
    private  var model: Register.Model

    private  var view: Register.View

    constructor(view: Register.View) {
        this.view = view
        this.model = RegisterModel(this)
    }

    override fun showMessageError(result: String?) {

        if (view != null){

            view.showMessageError(result)
        }
    }



    override fun goToNewDevice() {

        if (view != null){

            view.goToNewDevice()
        }
    }


    override fun createNewUser(name: String?, phone: String?, email: String?, adress: String?, userName: String?, password: String?) {

        if (
                !name.isNullOrEmpty() &&
                !phone.isNullOrEmpty()&&
                !email.isNullOrEmpty()&&
                !adress.isNullOrEmpty()&&
                !userName.isNullOrEmpty()&&
                !password.isNullOrEmpty()){

              model.createNewUser(name,phone,email,adress,userName,password)
        }else{
            Log.e("datos form else",name+" "+phone+" "+email+" "+adress+" "+userName+" "+password)
        }



    }




}
