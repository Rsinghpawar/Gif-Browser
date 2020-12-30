package com.rahul.gifbrowser.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.rahul.gifbrowser.R
import kotlinx.android.synthetic.main.fragment_gif.*


class GifFragment : Fragment(R.layout.fragment_gif) {

    private val args: GifFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()

    }

    private fun setUpViews() {
        val args = args.gifData
        img_single_gif.apply {
            Glide.with(this)
                .load(args!!.images.original.url)
                .placeholder(shimmerDrawable)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }
        tv_import_date.text = if (!args?.importDatetime.isNullOrEmpty()) args?.importDatetime?.dropLast(8)  else "N\\A"
        tv_username.text = if (!args?.username.isNullOrEmpty()) args?.username else "N\\A"
        tv_source.text = if (!args?.sourceTld.isNullOrEmpty()) args?.sourceTld else "N\\A"
        tv_rating.text = if (!args?.rating.isNullOrEmpty()) args?.rating else "N\\A"
        tv_gif_title.text = if (!args?.title.isNullOrEmpty()) args?.title else "N\\A"
    }

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
}