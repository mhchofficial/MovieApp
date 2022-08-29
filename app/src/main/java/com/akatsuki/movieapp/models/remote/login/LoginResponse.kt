package com.akatsuki.movieapp.models.remote.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("fullname")
    val fullname: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("session")
    val session: String?,
    @SerializedName("username")
    val username: String?
)