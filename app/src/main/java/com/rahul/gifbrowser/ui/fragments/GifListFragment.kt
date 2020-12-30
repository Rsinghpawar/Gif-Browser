package com.rahul.gifbrowser.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.rahul.gifbrowser.R
import com.rahul.gifbrowser.adapters.GifLoadStateAdapter
import com.rahul.gifbrowser.adapters.GifPagingAdapter
import com.rahul.gifbrowser.models.Data
import com.rahul.gifbrowser.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gif_list.*

@AndroidEntryPoint
class GifListFragment : Fragment(R.layout.fragment_gif_list), GifPagingAdapter.ItemClickListener {


    private val viewModel by viewModels<MainViewModel>()
    private  val adapter: GifPagingAdapter by lazy { GifPagingAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        setUpObservers()
        btn_retry.setOnClickListener {
            adapter.retry()
        }
    }

    private fun setUpObservers() {
        viewModel.gifsPagingRes.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    private fun setUpAdapter() {
        rv_main.adapter = adapter.withLoadStateHeaderAndFooter(
            header = GifLoadStateAdapter { adapter.retry() },
            footer = GifLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            progress_bar.isVisible = loadState.source.refresh is LoadState.Loading
            rv_main.isVisible = loadState.source.refresh is LoadState.NotLoading
            btn_retry.isVisible = loadState.source.refresh is LoadState.Error
            tv_error.isVisible = loadState.source.refresh is LoadState.Error
        }
    }


    override fun onItemClick(data: Data?, imageView: ImageView) {
        val action = GifListFragmentDirections.actionGifListFragmentToGifFragment(data)
        findNavController().navigate(action )
    }

}