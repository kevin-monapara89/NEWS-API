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
import com.kevin.newsapi.databinding.FragmentHealthBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HealthFragment : Fragment() {

    lateinit var binding: FragmentHealthBinding
    var category = "health"
    var newslist = ArrayList<ArticlesItem>()
    var adapter = NewsAdapter()
    lateinit var clickNow : ClickItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHealthBinding.inflate(layoutInflater)

        binding.nestedscroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                ApiClient.page++
                NewsCall(ApiClient.page)
            }
        })

        NewsCall(ApiClient.page)

        return binding.root
    }

    private fun NewsCall(page: Int) {
        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getHealth(ApiClient.country, category, ApiClient.apikey, ApiClient.page).enqueue(object :
            Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if (response.isSuccessful) {

                    clickNow = object : ClickItem {
                        override fun onClick(position: Int) {
                            var intent = Intent(
                                context,
                                ItemShowActivity::class.java
                            ).putExtra("pos", position)
                            startActivity(intent)
                        }
                    }

                    var news = response.body()?.articles
                    newslist.addAll(news as ArrayList<ArticlesItem>)

                    adapter.setNews(news)
                    binding.rcvhealth.layoutManager = LinearLayoutManager(context)
                    binding.rcvhealth.adapter = adapter
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                val e = Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}