package com.rahul.gifbrowser.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FixedHeightSmall(
    val url: String
) : Parcelable