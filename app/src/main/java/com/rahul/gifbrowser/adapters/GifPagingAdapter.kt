package com.rahul.gifbrowser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.rahul.gifbrowser.R
import com.rahul.gifbrowser.models.Data
import kotlinx.android.synthetic.main.item_gif_rv.view.*

class GifPagingAdapter(private val itemClickListener: ItemClickListener) :
    PagingDataAdapter<Data, GifPagingAdapter.MyViewHolder>(PHOTO_COMPARATOR) {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //For shimmer, this does not work if put in diff class
        private val shimmer = Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.9f)
            .setHighlightAlpha(0.8f)
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()
        private val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }

        fun bind(data: Data?) {
            itemView.img_gif.apply {
                Glide.with(this.context)
                    .asGif()
                    .placeholder(shimmerDrawable)
                    .load(data?.images?.fixed_height_small?.url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(this)
                itemView.setOnClickListener {
                    itemClickListener.onItemClick(data, this)
                }
            }
        }


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_gif_rv, parent, false)
        )
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Data,
                newItem: Data
            ): Boolean = oldItem == newItem

        }
    }

    interface ItemClickListener {
        fun onItemClick(data: Data?, imageView: ImageView)
    }
}