package com.akatsuki.movieapp.ViewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.integerResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akatsuki.movieapp.models.remote.Response.Data
import com.akatsuki.movieapp.utils.Resource
import com.akatsuki.movieapp.models.remote.Response.ResponseClass
import com.akatsuki.movieapp.models.remote.TopResponse.Result
import com.akatsuki.movieapp.models.remote.TopResponse.TopResponse
import com.akatsuki.movieapp.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ObjectStreamClass
import java.util.*
import javax.inject.Inject


@HiltViewModel
class ApiHomeViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    val loading = mutableStateOf(false)
    val upresult : MutableState<List<Data>> = mutableStateOf(ArrayList())
    val popresult : MutableState<List<Data>> = mutableStateOf(ArrayList())
    val Topresult : MutableState<List<Result>> = mutableStateOf(ArrayList())



    //we can use live Data but umm... its not good idea


    fun getUpdated(page: String){
        Log.e("the page", page)
        viewModelScope.launch {
            try {
                val newResult = repository.getUpdated(page)
                if (newResult is Resource.Success) {
                    upresult.value = newResult.data?.data!!
                }
                Log.e("the page", page.toString())
                println("the page$page")
                //loading.value = true


            }catch (ex : Exception){
                Log.e("error", ex.message.toString())
            }
        }

    }


    fun getPop(page: Int){
        viewModelScope.launch {
            try {
                val newResult = repository.getPop(page)
                if (newResult is Resource.Success) {
                    popresult.value = newResult.data?.data!!
                }
                Log.e("the page", page.toString())
                println("the page$page")

            }catch (ex : Exception){
                Log.e("error", ex.message.toString())
            }
        }

    }


    fun getTop(Url: String){
        viewModelScope.launch {
            try {
                val newResult = repository.getTop(Url)
                if (newResult is Resource.Success) {
                    Topresult.value = newResult.data?.result!!
                }
                loading.value = true


            }catch (ex : Exception){
                Log.e("error", ex.message.toString())
            }
        }

    }








}