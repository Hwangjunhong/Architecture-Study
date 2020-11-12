package com.hong.navermoviesearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hong.navermoviesearch.R
import com.hong.navermoviesearch.data.MovieData
import com.hong.navermoviesearch.ext.loadImageView
import com.hong.navermoviesearch.ext.toHtml
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
) {

    private val movieActor = itemView.tv_actor
    private val movieTitle = itemView.tv_title
    private val moviePoster = itemView.iv_poster
    private val movieDirector = itemView.tv_director
    private val movieRating = itemView.rating_bar
    private val movieOpenDate = itemView.tv_opening_date

    fun onBind(movieData: MovieData) {

        movieTitle.text = movieData.title.toHtml()
        movieActor.text = movieData.actor.toHtml()
        movieDirector.text = movieData.director.toHtml()
        movieOpenDate.text = movieData.pubDate.toHtml()
        moviePoster.loadImageView(movieData.image)
        movieRating.rating = (movieData.userRating.toFloatOrNull() ?: 0f) / 2

        //바인드를 할때마다 findViewById를 하기 때문에 비효율적임
//        itemView.apply {
//            tv_actor.text = movieData.actor
//            tv_director.text = movieData.director
//            tv_opening_date.text = movieData.pubDate
//            tv_title.text = HtmlCompat.fromHtml(movieData.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
//            rating_bar.rating = (movieData.userRating.toFloatOrNull() ?: 0f) / 2
//            iv_poster.loadGlideImageView(movieData.image)
//
        //온클릭리스너는 뷰홀더가 초기화될때 한번만 하면됨
        //여기서 하면 불필요한 set을 하게됨
//            setOnClickListener {
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieData.link))
//                context.startActivity(intent)
//            }
//        }
    }
}