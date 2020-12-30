package com.rahul.gifbrowser.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val id: String,
    val images: Images,
    @SerializedName("import_datetime")
    val importDatetime: String,
    val rating: String,
    @SerializedName("source_tld")
    val sourceTld: String,
    val title: String,
    @SerializedName("trending_datetime")
    val trendingDatetime: String,
    val url: String,
    val user: User,
    val username: String?
) : Parcelable