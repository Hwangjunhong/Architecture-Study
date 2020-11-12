package com.hong.navermoviesearch.ui

import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hong.navermoviesearch.data.MovieData
import com.hong.navermoviesearch.ext.click

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private val items = mutableListOf<MovieData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(parent).click {
            val webPage = Uri.parse(items[it].link)
            val webIntent = Intent(Intent.ACTION_VIEW, webPage)
            parent.context.startActivity(webIntent)
        }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    fun setData(item: List<MovieData>) {
        items.clear()
        items.addAll(item)
        notifyDataSetChanged()
    }


}