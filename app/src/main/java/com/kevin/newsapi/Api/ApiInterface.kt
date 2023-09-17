package com.kevin.newsapi.Api

import com.kevin.newsapi.Model.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines")
    fun getTopheadlines(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String,
        @Query("page") page: Int ,
        @Query("q") query: String
    ):Call<NewsModel>

    @GET("top-headlines")
    fun getSports(
        @Query("country") country : String,
        @Query("category") category: String,
        @Query("apiKey") apiKey : String,
        @Query("page") page: Int
    ):Call<NewsModel>

    @GET("top-headlines")
    fun getBussiness(
        @Query("country") country : String,
        @Query("category") category: String,
        @Query("apiKey") apiKey : String,
        @Query("page") page: Int
    ):Call<NewsModel>

    @GET("top-headlines")
    fun getScience(
        @Query("country") country : String,
        @Query("category") category: String,
        @Query("apiKey") apiKey : String,
        @Query("page") page: Int
    ):Call<NewsModel>

    @GET("top-headlines")
    fun getHealth(
        @Query("country") country : String,
        @Query("category") category: String,
        @Query("apiKey") apiKey : String,
        @Query("page") page: Int
    ):Call<NewsModel>

    @GET("top-headlines")
    fun getsearch(
        @Query("country") country : String,
        @Query("apiKey") apiKey : String,
        @Query("q") query: String
    ):Call<NewsModel>
}