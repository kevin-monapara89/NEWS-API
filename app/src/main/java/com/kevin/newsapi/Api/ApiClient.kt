package com.kevin.newsapi.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        val Base_url = "https://newsapi.org/v2/"
        var apikey = "ce3462900017405e812da50ab0932b08"
        var country = "in"
        var page = 1
        var q = String()
        var retrofit : Retrofit? = null

        fun getApiClient() : Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}