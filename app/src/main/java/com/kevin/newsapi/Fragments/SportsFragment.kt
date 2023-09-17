package com.kevin.newsapi.Fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.newsapi.Api.ApiClient
import com.kevin.newsapi.Api.ApiInterface
import com.kevin.newsapi.Model.ArticlesItem
import com.kevin.newsapi.Adapter.NewsAdapter
import com.kevin.newsapi.Model.NewsModel
import com.kevin.newsapi.databinding.FragmentSportsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportsFragment : Fragment() {

    lateinit var binding: FragmentSportsBinding
    var category = "sports"
    var newslist = ArrayList<ArticlesItem>()
    var adapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSportsBinding.inflate(layoutInflater)

        binding.nestedscroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                ApiClient.page ++
                NewsCall(ApiClient.page)
            }
        })

        NewsCall(ApiClient.page)

        return binding.root
    }
    private fun NewsCall(page: Int) {

        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getSports(ApiClient.country, category , ApiClient.apikey, ApiClient.page).enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if (response.isSuccessful) {
                    var news = response.body()?.articles
                    newslist.addAll(news as ArrayList<ArticlesItem>)

                    adapter.setNews(news)
                    binding.rcvsports.layoutManager = LinearLayoutManager(context)
                    binding.rcvsports.adapter = adapter
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                val e = Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })

    }
}