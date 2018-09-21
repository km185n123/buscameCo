package com.buscame.oncor.buscame.Services.Activity.Objects

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.buscame.oncor.buscame.Profile.Model.Service
import com.buscame.oncor.buscame.Profile.Model.ServiceType
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.Services.Activity.ListServices
import kotlinx.android.synthetic.main.cardview_type_services.view.*
import java.io.Serializable

class TypeServicesAdapter(private var context: Context, private var list: List<ServiceType>): RecyclerView.Adapter<TypeServicesAdapter.CustomViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_type_services, null)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val  item = list!![position]
        holder.name!!.text = item.name

        holder.itemView.setOnClickListener {

            var intent = Intent(context,ListServices::class.java)




            intent.putExtra("services", item.services.toHashSet())




            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return list?.size ?: 0
    }




    inner class CustomViewHolder(view: View):RecyclerView.ViewHolder(view){

        var name: TextView? = view.nameTypeServices
    }





}