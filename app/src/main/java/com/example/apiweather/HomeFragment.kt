package com.example.apiweather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.apiweather.databinding.FragmentHomeBinding
import org.json.JSONArray


class HomeFragment : Fragment() {

    private val apiUrl = "https://api.weatherapi.com/v1/forecast.json?key=73c090dadd0c4edfb10161015230810&q=Tashkent&days=4&aqi=no&alerts=no"
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentHomeBinding.inflate(inflater,container,false)
        val requestQueue = Volley.newRequestQueue(requireContext())
        var forecastAdapter = Adapterforecast(JSONArray())
        binding.forecastRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.bugunRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val request = JsonObjectRequest(apiUrl,
            { response ->
                val current = response.getJSONObject("current")
                val tempC = current.getDouble("temp_c")
                val windKph = current.getDouble("wind_kph")
                val humidity = current.getInt("humidity")

                binding.windSpeed.text = "${windKph}km/h"
                binding.humidity.text = "${humidity}%"
                binding.temp.text = "${tempC}°"
                forecastAdapter = Adapterforecast(response.getJSONObject("forecast").getJSONArray("forecastday"))
                binding.forecastRv.adapter = forecastAdapter
                binding.bugunRv.adapter = AdapterBugun(response.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getJSONArray("hour"))
                binding.icon.load("https:" + current.getJSONObject("condition").getString("icon"))
                forecastAdapter.notifyDataSetChanged()
                Log.d("TAG", "$response")

            }
        ) { error -> Log.d("TAG", "onErrorResponse: $error") }
        requestQueue.add(request)


        return  binding.root
    }

}