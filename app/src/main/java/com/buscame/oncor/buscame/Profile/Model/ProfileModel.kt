package com.buscame.oncor.buscame.Profile.Model

import com.buscame.oncor.buscame.Profile.ProfileMVP
import com.buscame.oncor.buscame.Profile.Rest.AccountUpdateRequest
import com.buscame.oncor.buscame.Profile.Rest.AccountUpdateResponse
import com.buscame.oncor.buscame.Profile.Rest.EndPointProfile
import com.buscame.oncor.buscame.RestApi.RestApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileModel:ProfileMVP.Model {

    private val restApiAdapter = RestApiAdapter()
    private val retrofit = restApiAdapter.conectionApiRest()

    val presenter:ProfileMVP.Presenter

    constructor(presenter: ProfileMVP.Presenter) {
        this.presenter = presenter
    }

    override fun editDataProfile(accessToken: String?, account: Account?) {

        val accountUpdateRequest =  AccountUpdateRequest()
        accountUpdateRequest.account = account

        val response : Call<AccountUpdateResponse> = retrofit
                .create(EndPointProfile::class.java)
                .updateDataProfile(accountUpdateRequest,"Bearer "+accessToken)

        response.enqueue(object : Callback<AccountUpdateResponse>{

            override fun onResponse(call: Call<AccountUpdateResponse>?, response: Response<AccountUpdateResponse>?) {

                if (response!!.code() == 200 && response.body() != null){

                   val message = response.body()!!.message.toString()

                    presenter.notification(message)
                    presenter.updateDataAccount(account)
                }
            }

            override fun onFailure(call: Call<AccountUpdateResponse>?, t: Throwable?) {
               presenter.notificationError(t!!.message)
            }



        })

    }
}