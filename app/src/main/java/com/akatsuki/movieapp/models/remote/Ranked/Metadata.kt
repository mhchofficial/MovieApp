package com.akatsuki.movieapp.models.remote.Ranked


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("current_page")
    val currentPage: String?,
    @SerializedName("page_count")
    val pageCount: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("total_count")
    val totalCount: Int?
)