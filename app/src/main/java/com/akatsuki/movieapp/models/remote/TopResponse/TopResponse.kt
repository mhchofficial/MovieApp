package com.akatsuki.movieapp.models.remote.TopResponse


import com.google.gson.annotations.SerializedName

data class TopResponse(
    @SerializedName("result")
    val result: List<Result>?
)