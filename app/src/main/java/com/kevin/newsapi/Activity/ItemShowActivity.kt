package com.kevin.newsapi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kevin.newsapi.Model.ArticlesItem
import com.kevin.newsapi.R
import com.kevin.newsapi.databinding.ActivityItemShowBinding

class ItemShowActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemShowBinding
    var newslist = ArrayList<ArticlesItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var img = intent.getStringExtra("img")
        var title = intent.getStringExtra("title")
        var description = intent.getStringExtra("description")

        Glide.with(this).load(img).into(binding.img)

        binding.txttitle.text = title
        binding.txtdescription.text = description

        binding.btnback.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            startActivity(intent)
        }
    }
}