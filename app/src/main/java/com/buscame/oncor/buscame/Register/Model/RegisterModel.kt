package com.buscame.oncor.buscame.Register.Model

import android.util.Log
import com.buscame.oncor.buscame.Profile.Model.Account
import com.buscame.oncor.buscame.Register.Register
import com.buscame.oncor.buscame.RestApi.RestApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterModel : Register.Model {

    private val restApiAdapter = RestApiAdapter()
    private val retrofit = restApiAdapter.conectionApiRest()


    private lateinit var presenter : Register.Presenter


    constructor(presenter: Register.Presenter) {
        this.presenter = presenter
    }


    override fun createNewUser(name: String?, phone: String?, email: String?, adress: String?, userName: String?, password: String?) {

        // conectar con retrofit y devolver la respuesta


        var accountDataRequest = AccountCreateRequest()
        var account: Account? = Account()

        account!!.name = name!!
        account.username = userName
        account.phone = phone
        account.address = email
        account.password = password


        accountDataRequest.account = account


        var response : Call<AccountCreateResponse> = retrofit
                .create(EndPointRegister::class.java)
                .createAcount(accountDataRequest)

        response.enqueue(object : Callback<AccountCreateResponse> {

            override fun onResponse(call: Call<AccountCreateResponse>?, response: Response<AccountCreateResponse>?) {
                Log.e("datos",response!!.body().toString())
                var code = response.code()

                if (response.code() == 200 && response.body() != null){

                    if (response.body()!!.code == 200){

                        presenter.goToNewDevice()
                    }else{
                        presenter.showMessageError(response.body()!!.message)
                    }

                }else{
                    presenter.showMessageError(response.message())
                }






            }

            override fun onFailure(call: Call<AccountCreateResponse>?, t: Throwable?) {
                Log.e("onFailure", t!!.message.toString())
            }

        })



    }




}