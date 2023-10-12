package com.example.apiweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject

class AdapterBugun(private val hours: JSONArray) : RecyclerView.Adapter<AdapterBugun.MyHolder>() {
    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val temp : TextView = itemView.findViewById(R.id.bugun_temp)
        val hours : TextView = itemView.findViewById(R.id.bugun_hours)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.bugun_item, parent, false))
    }

    override fun getItemCount(): Int {
        return hours.length()
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val hours :JSONObject = hours.getJSONObject(position)
        val time =hours.getString("time")
        val temp = hours.getDouble("temp_c")
             holder.hours.text = time.substring(time.length)
           holder.temp.text = temp.toString()

    }
}