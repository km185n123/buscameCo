package com.buscame.oncor.buscame.Historical.Activity.Object

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buscame.oncor.buscame.R
import android.widget.TextView
import android.widget.Toast
import com.buscame.oncor.buscame.Historical.Activity.HistoricalMaps
import com.buscame.oncor.buscame.Historical.Model.Historic
import kotlinx.android.synthetic.main.cardview_historical_points.view.*




class PointsHistoricalAdapter(private var context: Context, private var points: List<Historic>): RecyclerView.Adapter<PointsHistoricalAdapter.CustomViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_historical_points, null)

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {


        val point = points.get(position)


        holder.dateHistoric!!.text = point.stringCreateHour
        holder.addressHistoric!!.text = point.address

         var selected = true
        if ( !holder.itemView.isSelected ){
            selected = false

        }

        holder.itemView.setSelected(selected)

        holder.itemView.setOnFocusChangeListener { v, hasFocus ->
            Toast.makeText(context,hasFocus.toString(),Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnClickListener {
           HistoricalMaps.UpdatePintInMap(
                   point.latitude.toDouble(),
                   point.longitude.toDouble(),
                   HistoricalMaps.getmMap()
           )

       }


    }


    override fun getItemCount(): Int {

        return points?.size ?: 0
    }


    inner class CustomViewHolder(view: View):RecyclerView.ViewHolder(view){

        var dateHistoric: TextView? = view.dateHistoric
        var addressHistoric: TextView? = view.addressHistoric

    }







}