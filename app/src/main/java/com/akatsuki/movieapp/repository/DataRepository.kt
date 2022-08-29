package com.akatsuki.movieapp.repository

import android.content.Context
import com.akatsuki.movieapp.utils.Resource

import com.akatsuki.movieapp.data.local.UserDao
import com.akatsuki.movieapp.data.remote.InterfaceApi
import com.akatsuki.movieapp.models.local.UsersModel
import com.akatsuki.movieapp.models.remote.Ranked.RankedClass
import com.akatsuki.movieapp.models.remote.Response.Data
import com.akatsuki.movieapp.models.remote.Response.ResponseClass
import com.akatsuki.movieapp.models.remote.TopResponse.TopResponse
import com.akatsuki.movieapp.models.remote.detail.DetailResponse
import com.akatsuki.movieapp.models.remote.login.LoginResponse
import com.akatsuki.movieapp.utils.Resource.*
import com.akatsuki.movieapp.utils.alert_error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val api: InterfaceApi,
    private val dao: UserDao
) {

    //get all users from db
    fun getAllusers(): Flow<List<UsersModel>> = dao.getalluser()
        .flowOn(Dispatchers.Main)
        .conflate()

    //delete user BY ID
    suspend fun deleteuser(id: Int) {
        dao.deleteUser(id)
    }


    //etuser by id
    fun getUser(id: Int) = dao.getUserById(id)

    //delete ALL user
    suspend fun deleteusers() {
        dao.deleteUsers()
    }

    //Add users
    suspend fun adduser(users: UsersModel) {
        dao.saveUser(
            UsersModel(
                id = users.id,
                fullname = users.fullname,
                username = users.username,
                image = users.image,
                session = users.session,
            )
        )
    }


    //









    //remote



    suspend fun getUpdated(page:String): Resource<ResponseClass> {
        val response = try {
            api.getUpdated(page
            ).body()

        } catch (e: Exception) {
            return Error("An unknown error occurred.")
        }
        return Success(response!!)
    }




    suspend fun getPop(page:Int): Resource<ResponseClass> {
        val response = try {
            api.getPopular(page
            ).body()

        } catch (e: Exception) {
            return Error("An unknown error occurred.")
        }
        return Success(response!!)
    }


    suspend fun getTop(url:String): Resource<TopResponse> {
        val response = try {
            api.getTop(url
            ).body()

        } catch (e: Exception) {
            return Error("An unknown error occurred.")
        }
        return Success(response!!)
    }


    suspend fun getRank(page: Int): Resource<RankedClass> {
        val response = try {
            api.getRanked(page
            ).body()

        } catch (e: Exception) {
            return Error("An unknown error occurred.")
        }
        return Success(response!!)
    }

    suspend fun getLogin(url: String): Resource<LoginResponse> {
        val response = try {
            api.login(url = url
            ).body()

        } catch (e: Exception) {
            return Error("An unknown error occurred.")
        }
        return Success(response!!)
    }

    suspend fun SarchApi(query: String): Resource<RankedClass> {
        val response = try {
            api.getSearch(QuerySearch = query
            ).body()

        } catch (e: Exception) {
            return Error("An unknown error occurred.")
        }
        return Success(response!!)
    }


    suspend fun getDetail(id: Int): Resource<DetailResponse> {
        val response = try {
            api.getdetails(id = id
            ).body()

        } catch (e: Exception) {
            return Error("An unknown error occurred.")
        }
        return Success(response!!)
    }
}