package com.rahul.gifbrowser.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("banner_image")
    val bannerImage: String,
    @SerializedName("banner_url")
    val bannerUrl: String,
    val description: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("instagram_url")
    val instagramUrl: String,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("profile_url")
    val profileUrl: String,
    val username: String,
    @SerializedName("website_url")
    val websiteUrl: String
) : Parcelable