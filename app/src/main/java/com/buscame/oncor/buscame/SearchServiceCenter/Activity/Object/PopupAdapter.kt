package com.buscame.oncor.buscame.SearchServiceCenter.Activity.Object

import android.util.Log
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.buscame.oncor.buscame.SearchServiceCenter.Model.ServiceCenter
import com.buscame.oncor.buscame.Util.Util
import kotlinx.android.synthetic.main.info_marker_window.view.*


class PopupAdapter:GoogleMap.InfoWindowAdapter {


    var view: View? = null
    var models: MutableList<ServiceCenter>? = null

    constructor(view: View, serviceCenters: MutableList<ServiceCenter>) {



        this.view = view
        this.models = serviceCenters
    }


    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View? {

        val popup = view
        var position = marker.id.substring(1,2).toInt() -1


        Log.e("market 2",marker.snippet+" "+ marker.isFlat+" "+marker.alpha+" "+marker.zIndex+" "+marker.tag.toString()+" "+marker.showInfoWindow().toString())


        if (position >= 0){

            Log.e("data market",marker.zIndex.toString()+" "+marker.id+" "+models!!.size+"  "+ models!!.get(0).name+" "+position)

            if (popup != null) {
                popup.txtName.text = models!!.get(position).name
                popup.txtPhone.text = models!!.get(position).phone
                // popup.txtAddress.text = address
                popup.txtTimeArriva.text = "4 minutos"
                try {
                    popup.imgServiceCenter.setImageBitmap(Util.base64ToBitmap(models!!.get(position).image))

                } catch (e: Exception) {

                }
            }

        }



        return popup
    }


}