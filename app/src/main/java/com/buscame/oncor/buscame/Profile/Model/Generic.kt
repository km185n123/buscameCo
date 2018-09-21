package com.buscame.oncor.buscame.Profile.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import java.util.Date


open class Generic : Serializable {


    var id: Int = 0
     var name: String = ""
     var createDate: Date? = null
     var updateDate: Date?= null
    var version: Int = 0
}
