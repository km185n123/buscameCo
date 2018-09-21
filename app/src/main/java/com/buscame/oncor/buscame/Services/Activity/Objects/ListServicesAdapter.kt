package com.buscame.oncor.buscame.Services.Activity.Objects

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.buscame.oncor.buscame.Profile.Model.Service
import com.buscame.oncor.buscame.R
import com.buscame.oncor.buscame.SearchServiceCenter.Activity.findServicesCenter
import kotlinx.android.synthetic.main.cardview_service.view.*


class ListServicesAdapter(private var context: Context, private var list: List<Service>): RecyclerView.Adapter<ListServicesAdapter.CustomViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_service, null)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val item = list!![position]
        holder.name!!.text = item.name



        holder.itemView.setOnClickListener {


            val intent = Intent(context, findServicesCenter::class.java)
            intent.putExtra("ID_SERVICE",item.id)
            context.startActivity(intent)
        }


    }


    override fun getItemCount(): Int {
        return list?.size ?: 0
    }





    inner class CustomViewHolder(view: View):RecyclerView.ViewHolder(view){

        var name: TextView? = view.nameServices
    }





}