package com.hong.architecturestudy.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.repository.RepositoryDataSource
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListDialogFragment(private val getMovieTitle: GetMovieTitle) : DialogFragment() {

    private val repositoryDataSourceImpl: RepositoryDataSource by lazy {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl()
        RepositoryDataSourceImpl(localDataSourceImpl, remoteDataSourceImpl)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_fragment_movie_list, null)
        val rvSearchItem = view?.findViewById<RecyclerView>(R.id.rv_search_list)

        val movieSearchListAdapter = MovieSearchListAdapter {
            getMovieTitle(it)
            dismiss()
        }

        rvSearchItem?.adapter = movieSearchListAdapter
        rvSearchItem?.setHasFixedSize(true)

        lifecycleScope.launch(Dispatchers.Default) {
            movieSearchListAdapter.setList(repositoryDataSourceImpl.loadResentSearchQuery())
        }

        return AlertDialog.Builder(requireActivity())
            .setView(view)
            .setTitle("최근 검색")
            .create()
    }

    companion object {
        @Volatile
        private var INSTANCE: MovieListDialogFragment? = null

        fun getInstance(param: (String) -> Unit): MovieListDialogFragment =
            INSTANCE ?: synchronized(this) {
                MovieListDialogFragment(param).also { INSTANCE = it }
            }
    }
}