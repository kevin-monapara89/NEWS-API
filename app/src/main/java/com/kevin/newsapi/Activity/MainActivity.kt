package com.kevin.newsapi.Activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.kevin.newsapi.Adapter.FragmentAdapter
import com.kevin.newsapi.Adapter.NewsAdapter
import com.kevin.newsapi.Api.ApiClient
import com.kevin.newsapi.Fragments.BussinessFragment
import com.kevin.newsapi.Fragments.HealthFragment
import com.kevin.newsapi.Fragments.ScienceFragment
import com.kevin.newsapi.Fragments.SportsFragment
import com.kevin.newsapi.Fragments.TopHeadlines
import com.kevin.newsapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var item = arrayOf(
        "Top-Headlines",
        "Bussiness",
        "Sports",
        "Science",
        "Health"
    )
    var fragments = arrayOf(
        TopHeadlines(),
        BussinessFragment(),
        SportsFragment(),
        ScienceFragment(),
        HealthFragment()
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.adapter = FragmentAdapter(supportFragmentManager, fragments, item)
        binding.tabitem.setupWithViewPager(binding.viewpager)

        if (NETWORK_STATS_SERVICE != null) {
            binding.maincontent.isVisible = true
            binding.nointernet.isVisible = false
        } else {
            binding.nointernet.setBackgroundColor(Color.BLUE)
        }

        binding.imgsearch.setOnClickListener {
            binding.imgsearch.isVisible = false
            binding.searchnws.isVisible = true
        }

        binding.btnsearch.setOnClickListener {
            var query = binding.edtsearch.toString()
            ApiClient.q = query
        }

        binding.cancle.setOnClickListener {
//            binding.fragframe.isVisible = true
//            binding.rcvsearch.isVisible = false

            binding.edtsearch.setText("")
            binding.searchnws.isVisible = false
            binding.imgsearch.isVisible = true
        }


    }

}