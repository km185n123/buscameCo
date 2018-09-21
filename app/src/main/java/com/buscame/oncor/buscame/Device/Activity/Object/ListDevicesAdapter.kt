package com.buscame.oncor.buscame.Device.Activity.Object

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.buscame.oncor.buscame.Device.Activity.EditDevice
import com.buscame.oncor.buscame.Device.Model.Device
import com.buscame.oncor.buscame.Device.Presenter.DevicePresenter
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.Util.DataCache
import com.buscame.oncor.buscame.Util.Util





class ListDevicesAdapter(private var context: Activity, private var list: List<Device>,val presenter: DevicePresenter): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_device , parent,false)
        return CustomViewHolder(view)
    }




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val  item = list!![position]

        Log.e("position device",position.toString())

        val  customViewHolder = holder as CustomViewHolder

        val alias = if (!item.alias.isNullOrEmpty()) item.alias else item.name
        customViewHolder.name?.text = alias
        customViewHolder.desciptionDevice?.text = item.description

        customViewHolder.btnEditDevice.setOnClickListener {

             val intent = Intent(context, EditDevice::class.java)
             intent.putExtra("DEVICE_CODE", item.code)
             context.startActivityForResult(intent,1)

        }




        customViewHolder.btnDeleteDevice.setOnClickListener {

            val accessToken = DataCache.readData(context,"accessToken")
            presenter.removeDevice(item.code,position,accessToken)

        }



        if (!item.image.isNullOrEmpty()){

            holder.photoDevice?.setImageBitmap(Util.decodeBase64(item.image))
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }





    inner class CustomViewHolder(view: View):RecyclerView.ViewHolder(view){

        var name: TextView? = view.findViewById(R.id.nameDevice)
        var desciptionDevice: TextView? = view.findViewById(R.id.desciption_device)
        var photoDevice: ImageView? = view.findViewById(R.id.photoDevice)
        var btnEditDevice :TextView = view.findViewById(R.id.btnEditDevice)
        var btnDeleteDevice :TextView = view.findViewById(R.id.btnDeleteDevice)
    }







}
