package com.buscame.oncor.buscame.Historical.Activity.Object

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buscame.oncor.buscame.R
import android.widget.TextView
import com.buscame.oncor.buscame.Historical.Activity.HistoricalMaps
import com.buscame.oncor.buscame.Historical.Model.Historic
import kotlinx.android.synthetic.main.cardview_historical.view.*
import java.util.*


class HistoricalAdapter(private var context: Context, private var historicForDay: Map<String, List<Historic>>): RecyclerView.Adapter<HistoricalAdapter.CustomViewHolder>() {


    val keys = historicForDay.keys.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_historical, null)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {



        val key = keys.get(position)



        holder.itemView.setOnClickListener {

            var intent = Intent(context, HistoricalMaps::class.java)

            intent.putExtra("POINTS_HISTORICAL",historicForDay.get(key)!!.toHashSet())
            context.startActivity(intent)
        }



        holder.dateHistoric!!.text = key


    }


    override fun getItemCount(): Int {
        return keys?.size ?: 0
    }


    inner class CustomViewHolder(view: View):RecyclerView.ViewHolder(view){

        var dateHistoric: TextView? = view.dateHistoric

    }







}