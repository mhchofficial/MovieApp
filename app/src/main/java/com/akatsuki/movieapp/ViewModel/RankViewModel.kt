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
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.akatsuki.movieapp.models.remote.Ranked.Data
import com.akatsuki.movieapp.models.remote.Ranked.RankedClass
import com.akatsuki.movieapp.models.remote.login.LoginResponse
import com.akatsuki.movieapp.utils.Resource

import com.akatsuki.movieapp.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

import javax.inject.Inject

const val PAGE_LIMIT = 25
const val PAGE_COUNT = 10

@HiltViewModel
class RankViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    val loading = mutableStateOf(false)
    val response: Flow<PagingData<Data>> =
        Pager(PagingConfig(pageSize = 6)) {

            RankedDetailSource(repository)
        }.flow.cachedIn(viewModelScope)







    val result: MutableState<List<Data>> = mutableStateOf(ArrayList())

    fun getSearch(query: String){
        viewModelScope.launch {
            //bc api to fast and we want to see progressbar
            delay(2000)
            try {
                val newResult = repository.SarchApi(query = query)
                if (newResult is Resource.Success) {
                    result.value = newResult.data?.data!!
                }
                loading.value = true


            }catch (ex : Exception){
                Log.e("error", ex.message.toString())
            }
        }

    }











}