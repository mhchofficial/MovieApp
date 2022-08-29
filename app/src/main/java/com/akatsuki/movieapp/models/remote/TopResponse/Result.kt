package com.akatsuki.movieapp.models.remote.TopResponse


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("poster")
    val poster: String?,
    @SerializedName("title")
    val title: String?
)