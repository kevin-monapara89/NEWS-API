package com.kevin.newsapi.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.kevin.newsapi.Activity.ItemShowActivity
import com.kevin.newsapi.ClickItem
import com.kevin.newsapi.Model.ArticlesItem
import com.kevin.newsapi.databinding.NewsitemBinding

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsHolder>()  {

    lateinit var context : Context
    var newslist = ArrayList<ArticlesItem>()

    class NewsHolder(itemView: NewsitemBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        context = parent.context
        var binding = NewsitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsHolder(binding)
    }

    override fun getItemCount(): Int {
        return newslist.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.binding.apply {
            newslist.get(position).apply {
                txttitle.text = title
                txtdescriptin.text = description
                Glide.with(context).load(urlToImage).into(imgposter)

                holder.itemView.setOnClickListener {
                    val intent = Intent(it.context, ItemShowActivity::class.java)

                    intent.putExtra("img", newslist.get(position).urlToImage)
                    intent.putExtra("title", newslist.get(position).title)
                    intent.putExtra("description", newslist.get(position).description)

                    it.context.startActivity(intent)
                }
            }
        }
    }

    fun setNews(news: List<ArticlesItem>?) {
        this.newslist = (news as ArrayList<ArticlesItem>?)!!
    }
}