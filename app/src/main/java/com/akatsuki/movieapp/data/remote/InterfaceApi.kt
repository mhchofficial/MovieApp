package com.akatsuki.movieapp.data.remote


import com.akatsuki.movieapp.models.remote.Ranked.RankedClass
import com.akatsuki.movieapp.models.remote.Response.ResponseClass
import com.akatsuki.movieapp.models.remote.TopResponse.TopResponse
import com.akatsuki.movieapp.models.remote.detail.DetailResponse
import com.akatsuki.movieapp.models.remote.login.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface InterfaceApi {
    @GET
    suspend fun getTop(
        @Url url:String
    ): Response<TopResponse>

    //ita all same api but we used three time with different name
    @GET("v1/movies")
    suspend fun getUpdated(
        @Query("page") page:String
    ): Response<ResponseClass>

    @GET("v1/movies")
    suspend fun getPopular(
        @Query("page") page:Int
    ): Response<ResponseClass>

    @GET("v1/movies")
    suspend fun getRanked(
        @Query("page") page:Int
    ): Response<RankedClass>

    //in this time our public api not have login so we just create in other host so basically we use Url to request to other server
    @GET
    suspend fun login(
        @Url url:String
    ): Response<LoginResponse>



    @GET("v1/movies")
    suspend fun getSearch(
        @Query("q") QuerySearch:String
    ): Response<RankedClass>


    @GET("v1/movies/{id}")
    suspend fun getdetails(
        @Path("id") id:Int
    ): Response<DetailResponse>



}