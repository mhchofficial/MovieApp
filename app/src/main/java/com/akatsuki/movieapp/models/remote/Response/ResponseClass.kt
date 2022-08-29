package com.akatsuki.movieapp.models.remote.Response


import com.google.gson.annotations.SerializedName

data class ResponseClass(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("metadata")
    val metadata: Metadata?
)