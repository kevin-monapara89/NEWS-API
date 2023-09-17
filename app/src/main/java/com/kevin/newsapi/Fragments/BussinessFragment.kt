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
import com.kevin.newsapi.databinding.FragmentBussinessBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BussinessFragment : Fragment() {

    lateinit var binding: FragmentBussinessBinding
    var category = "business"
    var newslist = ArrayList<ArticlesItem>()
    var adapter = NewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBussinessBinding.inflate(layoutInflater)

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
        api.getBussiness(ApiClient.country, category , ApiClient.apikey, ApiClient.page).enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if (response.isSuccessful) {
                    var news = response.body()?.articles
                    newslist.addAll(news as ArrayList<ArticlesItem>)

                    adapter.setNews(news)
                    binding.rcvbussiness.layoutManager = LinearLayoutManager(context)
                    binding.rcvbussiness.adapter = adapter
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                val e = Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}