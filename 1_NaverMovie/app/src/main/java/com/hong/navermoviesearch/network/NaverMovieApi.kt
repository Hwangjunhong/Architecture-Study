package com.hong.navermoviesearch.network

import com.hong.navermoviesearch.data.MovieResultData
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverMovieApi {

    @GET("v1/search/movie.json")
    fun getMovies(
        @Query("query") query: String
    ): retrofit2.Call<MovieResultData>

}