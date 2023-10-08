package com.example.apiweather

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    private val apiUrl = "https://api.weatherapi.com/v1/forecast.json?key=11b9394e7e024a2588a44954230610&q=Tashkent&days=8&aqi=no&alerts=no"
    private lateinit var tempTextView: TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val requestQue = Volley.newRequestQueue(this)


        tempTextView = findViewById(R.id.temp)

        val request = JsonObjectRequest(apiUrl,
            { response ->
                val current = response.getJSONObject("current")

                val tempC = current.getDouble("temp_c")
                val windKph = current.getDouble("wind_kph")
                val humidity = current.getInt("humidity")

                findViewById<TextView>(R.id.wind_speed).text = "${windKph}km/h"
                findViewById<TextView>(R.id.humidity).text = "${humidity}%"

                tempTextView.text = "${tempC}°"

            }
        ) { error -> Log.d("TAG", "onErrorResponse: $error") }
        requestQue.add(request)

    }
}