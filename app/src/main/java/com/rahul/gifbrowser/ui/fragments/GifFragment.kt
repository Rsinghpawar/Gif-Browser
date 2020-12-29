package com.rahul.gifbrowser.ui.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.rahul.gifbrowser.R
import kotlinx.android.synthetic.main.fragment_gif.*


class GifFragment : Fragment(R.layout.fragment_gif) {

    private val args: GifFragmentArgs by navArgs()
    private val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(1800)
        .setBaseAlpha(0.8f)
        .setHighlightAlpha(0.7f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()
    private val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        img_single_gif.apply {
            transitionName = args.gifData!!.images.original.url
            Glide.with(this)
                .load(args.gifData?.images?.downsized?.url)
                .placeholder(shimmerDrawable)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
            transitionName = args.gifData!!.images.original.url
        }
    }
}