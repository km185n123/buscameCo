package com.buscame.oncor.buscame.Historical.Presenter

import com.buscame.oncor.buscame.Historical.HistoricalMVP
import com.buscame.oncor.buscame.Historical.Model.Historic
import com.buscame.oncor.buscame.Historical.Model.HistoricalModel
import java.util.*

class HistoricalPresenter:HistoricalMVP.Presenter {
    var view:HistoricalMVP.View

    var model:HistoricalMVP.Model

    constructor(view:HistoricalMVP.View){
        this.view = view
        this.model = HistoricalModel(this)
    }
    override fun notification(message: String) {

        if (view != null){

            view.notification(message)
        }
    }

    override fun notificationError(error: String) {

        if (view != null){

            view.notificationError(error)
        }

    }

    override fun loadHistoric(historicForDay: Map<String, List<Historic>>) {
        if (view != null){

            view.loadHistoric(historicForDay)
        }

    }

    override fun historical(startDate: Date, endDate: Date, codeDevice: String,accessToken:String) {

        model.historical(startDate,endDate,codeDevice,accessToken)
    }


}