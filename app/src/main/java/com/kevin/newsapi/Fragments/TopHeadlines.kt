package com.kevin.newsapi.Fragments

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.newsapi.Activity.ItemShowActivity
import com.kevin.newsapi.Api.ApiClient
import com.kevin.newsapi.Api.ApiInterface
import com.kevin.newsapi.Model.ArticlesItem
import com.kevin.newsapi.Adapter.NewsAdapter
import com.kevin.newsapi.ClickItem
import com.kevin.newsapi.Model.NewsModel
import com.kevin.newsapi.databinding.FragmentTopheadlinesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopHeadlines : Fragment() {

    lateinit var binding: FragmentTopheadlinesBinding
    var adapter = NewsAdapter()
    var newslist = ArrayList<ArticlesItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTopheadlinesBinding.inflate(layoutInflater)

        binding.nestedscroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                ApiClient.page++
                adapter.newslist
                NewsCall(ApiClient.page)
            }
        })

        NewsCall(ApiClient.page)
        return binding.root
    }

    fun NewsCall(page: Int) {

        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getTopheadlines(ApiClient.country, ApiClient.apikey, ApiClient.page, ApiClient.q)
            .enqueue(object : Callback<NewsModel> {
                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    if (response.isSuccessful) {
//
                        var clickNow = object : ClickItem {
                            override fun onClick(position: Int) {
                                var intent = Intent(context,ItemShowActivity::class.java).putExtra("pos", position)
                                startActivity(intent)
                            }
                        }
                        var news = response.body()?.articles
                        newslist.addAll(news as ArrayList<ArticlesItem>)

                        adapter.setNews(news)
                        binding.rcvallnews.layoutManager = LinearLayoutManager(context)
                        binding.rcvallnews.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                    val e = Log.e(ContentValues.TAG, "onFailure: ${t.message}")
                }
            })
    }
}