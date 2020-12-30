package com.rahul.gifbrowser.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Images(
    val original: Original,
    val fixed_height_small : FixedHeightSmall
) : Parcelable