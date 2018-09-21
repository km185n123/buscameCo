package com.buscame.oncor.buscame.Historical

import com.buscame.oncor.buscame.Historical.Model.Historic
import java.util.*

interface HistoricalMVP {

    interface View{

        fun notification(message:String)
        fun notificationError(error:String)
        fun loadHistoric( historicForDay: Map<String, List<Historic>>)
    }

    interface Presenter{

        fun notification(message:String)
        fun notificationError(error:String)
        fun loadHistoric( historicForDay: Map<String, List<Historic>>)
        fun historical(startDate: Date, endDate:Date, codeDevice:String,accessToken:String)
    }

    interface Model{

        fun historical(startDate: Date, endDate:Date, codeDevice:String,accessToken:String)
    }
}