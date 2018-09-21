package com.buscame.oncor.buscame.Login.Login.Model

import android.content.Context
import android.util.Log
import com.buscame.oncor.buscame.Device.Rest.EndPointDevice
import com.buscame.oncor.buscame.Device.Rest.Response.DeviceFindAllByUserResponse
import com.buscame.oncor.buscame.Login.Login.Login
import com.buscame.oncor.buscame.Profile.Model.Account
import com.buscame.oncor.buscame.RestApi.RestApiAdapter
import com.buscame.oncor.buscame.Util.DataCache

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel : Login.Model {

    private val restApiAdapter = RestApiAdapter()
    private val TAG = "LoginModel"
    private val retrofit = restApiAdapter.conectionApiRest()
    private lateinit var presenter : Login.Presenter
    private var count = true

    constructor(presenter: Login.Presenter){

        this.presenter = presenter
    }

    /**
     *
     *
     */
    override fun loginUser(user: String?, password: String?, context: Context?) {

      var p = this.presenter

       var response : Call<Authorization>  = retrofit
               .create(EndPointAuth::class.java)
               .login(
                       "Basic YnVzY2FtZUFwcDo4dTVjYW0z",
                       "password",
                       user,
                       password
               )

        response.enqueue(object : Callback<Authorization>{

            override fun onResponse(call: Call<Authorization>?, response: Response<Authorization>?) {
               Log.e("datos",response!!.body().toString())


                Log.e("datos",response!!.body().toString())
                var code = response.code()

                if (code == 200 && response.body() != null){

                    var accessToken = response.body()!!.accessToken.toString()
                    var account = response.body()!!.account
                    var refreshToken = response.body()!!.refreshToken

                   listServices(accessToken,account,refreshToken,context)

                }else{

                    presenter.showMessage("Usuario o contraseña incorrecto")
                }


            }

            override fun onFailure(call: Call<Authorization>?, t: Throwable?) {
                Log.e("onFailure", t!!.message.toString())

                presenter.showMessage(t.toString())
            }

        })


    }


    /**
     *
     *
     */
    private fun  listServices(accessToken: String, account: Account, refreshToken: String, context: Context?) {

        var response : Call<DeviceFindAllByUserResponse> = retrofit
                .create(EndPointDevice::class.java)
                .findAll("Bearer "+accessToken)

        response.enqueue(object : Callback<DeviceFindAllByUserResponse> {

            override fun onResponse(call: Call<DeviceFindAllByUserResponse>?, response: Response<DeviceFindAllByUserResponse>?) {

                if (response!!.code() == 200 && response.body() != null){

                    //presenter.goToDashBoard()
                    if (response.body()!!.code == 200 && !response.body()!!.devices.isEmpty()){


                        presenter.goToDashBoard(accessToken,response.body(),account,refreshToken)

                    }else{
                        presenter.goToFirstNewDevice(accessToken)
                    }


                }else{
                    presenter.showMessage("error devices")
                }

                if(response.code() == 401){

                    val refreshToken = DataCache.readData(context,"refreshToken")
                    refresToken(refreshToken,context,count)

                }
            }

            override fun onFailure(call: Call<DeviceFindAllByUserResponse>?, t: Throwable?) {
                presenter.showMessage("failure")
            }

        })

    }

    /**
     *
     *
     */
    override fun closeSession(accesstoken: String?, context: Context?) {


        var response : Call<OauthLogoutResponse> = retrofit
                .create(EndPointAuth::class.java)
                .logout("Bearer "+accesstoken)

        response.enqueue(object :Callback<OauthLogoutResponse>{

            override fun onResponse(call: Call<OauthLogoutResponse>?, response: Response<OauthLogoutResponse>?) {

                if (response!!.code() == 200 && response.body() != null){

                    if (response.body()!!.message.equals("ok"))
                       presenter.goToLogin()
                }

                if(response.code() == 401){

                  val refreshToken = DataCache.readData(context,"refreshToken")
                    refresToken(refreshToken,context,count)

                }

            }

            override fun onFailure(call: Call<OauthLogoutResponse>?, t: Throwable?) {
               Log.e(TAG,t!!.message)

            }

        })

    }

    /**
     *
     *
     */
    private fun refresToken(refresToken: String, context: Context?, count: Boolean) {


            var response: Call<Authorization> = retrofit
                    .create(EndPointAuth::class.java)
                    .refresh_token("Basic YnVzY2FtZUFwcDo4dTVjYW0z","refresh_token",refresToken)


        response.enqueue(object : Callback<Authorization>{

            override fun onResponse(call: Call<Authorization>?, response: Response<Authorization>?) {
                Log.e("datos",response!!.body().toString())


                Log.e("datos",response!!.body().toString())
                var code = response.code()

                if (code == 200 && response.body() != null){

                    var accessToken = response.body()!!.accessToken.toString()
                    var account = response.body()!!.account
                    var refreshToken = response.body()!!.refreshToken

                    listServices(accessToken,account,refreshToken,context)

                }

            }

            override fun onFailure(call: Call<Authorization>?, t: Throwable?) {
                Log.e("onFailure", t!!.message.toString())
            }

        })

    }


}
