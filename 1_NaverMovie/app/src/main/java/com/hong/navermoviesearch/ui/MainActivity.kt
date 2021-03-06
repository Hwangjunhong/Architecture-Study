package com.hong.navermoviesearch.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hong.navermoviesearch.R
import com.hong.navermoviesearch.data.MovieResultData
import com.hong.navermoviesearch.ext.hideKeyboard
import com.hong.navermoviesearch.network.RetrofitCreator
import com.hong.navermoviesearch.utils.log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val adapter = MovieAdapter()
    private val movieCallBack = object : retrofit2.Callback<MovieResultData> {
        override fun onFailure(call: Call<MovieResultData>, t: Throwable) {
            log("[MainActivity] : 통신 실패")
        }

        override fun onResponse(call: Call<MovieResultData>, response: Response<MovieResultData>) {
            with(response) {
                val body = body()
                if (isSuccessful && body != null) {
                    adapter.setData(body.items)
                } else {
                    log("[MainActivity] : 데이터 불러오기 실패")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        btn_search.setOnClickListener {
            if (edit_search.text.toString().isBlank()) {
                Toast.makeText(this, "영화 제목을 입력해 주세요", Toast.LENGTH_LONG).show()
            } else {
                getMovieList(edit_search.text.toString())
                hideKeyboard(this, edit_search)
            }
        }
    }

    private fun setRecyclerView() {
        rv_movies_list.adapter = adapter
        rv_movies_list.setHasFixedSize(true)
    }

    private fun getMovieList(query: String) {
        RetrofitCreator.service.getMovies(query).enqueue(movieCallBack)
    }

}