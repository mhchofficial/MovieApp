package com.akatsuki.movieapp.models.remote.Ranked


import com.google.gson.annotations.SerializedName

data class RankedClass(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("metadata")
    val metadata: Metadata?
)