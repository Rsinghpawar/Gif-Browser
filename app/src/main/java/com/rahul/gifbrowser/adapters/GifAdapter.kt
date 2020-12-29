package com.rahul.gifbrowser.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahul.gifbrowser.R
import com.rahul.gifbrowser.models.Data
import kotlinx.android.synthetic.main.item_gif_rv.view.*

class GifAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<GifAdapter.MyViewHolder>() {

    private var list = emptyList<Data>()

    fun setData(list: List<Data>) {
        this.list = list
        notifyDataSetChanged()
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

//            itemView.img_gif.transitionName = list[position].images.original.url
            Glide.with(itemView.context).load(list[position].images.fixed_height_small.url)
                .into(itemView.img_gif)
            itemView.setOnClickListener {
                itemClickListener.onItemClick(list[position], itemView.img_gif)
            }
//            val gif = list[position]
//            itemView.img_gif.transitionName = gif.username
//            itemView.img_gif.apply {
//                Glide.with(this).load(gif.images.fixed_height_small.url).into(this)
//                this.setOnClickListener {
//                    itemClickListener.onItemClick(gif, this)
//                }
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_gif_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list.size

    interface ItemClickListener {
        fun onItemClick(data: Data, imageView: ImageView)
    }
}