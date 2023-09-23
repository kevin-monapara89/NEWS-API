package com.kevin.newsapi.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
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
        var content = intent.getStringExtra("content")
        var publisher = intent?.getStringExtra("publisher")
        var date = intent.getStringExtra("date")

        Glide.with(this).load(img).into(binding.img)
        binding.txttitle.text = title
        binding.txtdescription.text = description
        binding.txtcontent.text = content
        binding.txtpublisher.text = publisher
        binding.txtdate.text = date

        binding.btnback.setOnClickListener{
            val onBackPressedCallback = object  : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    Toast.makeText(this@ItemShowActivity, "Back", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            onBackPressedDispatcher.addCallback(onBackPressedCallback)
        }

    }
}