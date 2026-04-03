package com.route.newsc43.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object {
        const val API_KEY = "337dc2b5fe7c467aacde1b358cbe785b"
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        fun getWebServices(): WebServices = retrofit.create(WebServices::class.java)
    }
}