package com.buscame.oncor.buscame.Profile.Activity.Object

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buscame.oncor.buscame.R
import android.widget.TextView
import kotlinx.android.synthetic.main.cardview_feature.view.*


class featuresAdadpter( private var list: List<String>): RecyclerView.Adapter<featuresAdadpter.CustomViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_feature, null)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val item = list.get(position)
        holder.featureText!!.text = item

    }


    override fun getItemCount(): Int {
        return list?.size ?: 0
    }


    inner class CustomViewHolder(view: View):RecyclerView.ViewHolder(view){

        var featureText: TextView? = view.txtFeature

    }







}