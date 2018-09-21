package com.buscame.oncor.buscame.Profile.Presenter

import com.buscame.oncor.buscame.Profile.Model.Account
import com.buscame.oncor.buscame.Profile.Model.ProfileModel
import com.buscame.oncor.buscame.Profile.ProfileMVP

class ProfilePresenter:ProfileMVP.Presenter {

    val view:ProfileMVP.View
    val model:ProfileMVP.Model

    constructor(view: ProfileMVP.View){

        this.view = view
        model = ProfileModel(this)
    }

    override fun notification(message: String?) {

        if (view != null){

            view.notification(message)
        }
    }

    override fun notificationError(error: String?) {

        if (view != null){

            view.notificationError(error)
        }
    }

    override fun updateDataAccount(account: Account?) {

        if (view != null){

            view.updateDataAccount(account)
        }
    }

    override fun editDataProfile(accessToken: String?, account: Account?) {

        model.editDataProfile(accessToken,account)
    }
}