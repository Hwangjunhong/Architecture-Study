package com.hong.architecturestudy.data.source.remote

import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.data.model.MovieResultData
import com.hong.architecturestudy.data.network.RetrofitCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val retrofitCreator: RetrofitCreator
) : RemoteDataSource {

    override fun getMovieList(
        query: String,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val naverMovieApi = retrofitCreator.service
        naverMovieApi.getMovies(query).enqueue(object : Callback<MovieResultData> {
            override fun onFailure(call: Call<MovieResultData>, t: Throwable) {
                onFailure.invoke(t)
            }

            override fun onResponse(
                call: Call<MovieResultData>,
                response: Response<MovieResultData>
            ) {
                with(response) {
                    val body = body()
                    if (isSuccessful && body != null) {
                        onSuccess.invoke(body.items)
                    } else {
                        onFailure.invoke(Throwable())
                    }
                }
            }
        })
    }
}

