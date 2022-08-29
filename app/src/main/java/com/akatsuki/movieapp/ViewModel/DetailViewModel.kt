package com.akatsuki.movieapp.ViewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akatsuki.movieapp.models.remote.detail.DetailResponse
import com.akatsuki.movieapp.repository.DataRepository
import com.akatsuki.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    val loading = mutableStateOf(false)
    val result : MutableState<DetailResponse?> = mutableStateOf(null)

    fun getDetails(id: Int){
        viewModelScope.launch {
            try {
                val newResult = repository.getDetail(id)
                if (newResult is Resource.Success) {
                    result.value = newResult.data
                }
                loading.value = true


            }catch (ex : Exception){
                Log.e("error", ex.message.toString())
            }
        }

    }

}