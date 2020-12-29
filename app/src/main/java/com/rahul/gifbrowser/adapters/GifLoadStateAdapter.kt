package com.rahul.gifbrowser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahul.gifbrowser.R
import kotlinx.android.synthetic.main.giphy_load_state_footer.view.*

class GifLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<GifLoadStateAdapter.LoadStateViewHolder>() {


    inner class LoadStateViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.btn_retry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bindView(loadState: LoadState) {
            itemView.apply {
                progress_bar.isVisible = loadState is LoadState.Loading
                btn_retry.isVisible = loadState !is LoadState.Loading
                tv_error.isVisible = loadState !is LoadState.Loading
            }
        }

    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindView(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.giphy_load_state_footer, parent, false)
        )
    }


}