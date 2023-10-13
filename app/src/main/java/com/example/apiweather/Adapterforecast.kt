package com.example.apiweather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.json.JSONArray
import org.json.JSONObject

class Adapterforecast (var days:JSONArray, var itemClickInterface: ItemClickInterface):RecyclerView.Adapter<Adapterforecast.MyHolder>() {
    class MyHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.bugun_icon)
        val day: TextView = itemView.findViewById(R.id.forecast_day)
        val temp: TextView = itemView.findViewById(R.id.forecast_temp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.forecast_item,parent,false))

    }
    override fun getItemCount(): Int {
        return days.length()
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        val day = days.getJSONObject(position)
        val date = day.getString("date")
        val temp = day.getJSONObject("day").getDouble("avgtemp_c")
        val iconUrl = day.getJSONObject("day").getJSONObject("condition").getString("icon")

        holder.day.text = date
        holder.temp.text = temp.toInt().toString() + "°C"
        holder.icon.load("https:" + iconUrl)
        holder.itemView.setOnClickListener {
            itemClickInterface.onParentClick(day, position)


       }
    }
    interface ItemClickInterface{
        fun onParentClick(day : JSONObject, position: Int)
    }


}

