package com.akatsuki.movieapp.ViewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import com.akatsuki.movieapp.models.local.SaveModel
import com.akatsuki.movieapp.models.local.UsersModel
import com.akatsuki.movieapp.models.remote.Ranked.Data
import com.akatsuki.movieapp.repository.DataRepository
import com.akatsuki.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {
    val favList: LiveData<List<SaveModel>> = repository.getAllFav().asLiveData()



    fun addFav(item: SaveModel) {
        viewModelScope.launch {
            repository.addfav(item)
        }
    }

    fun deleteFav(id: Int) {
        viewModelScope.launch {
            repository.deleteFav(id = id)
        }
    }

    fun get_fav(id: Int): LiveData<SaveModel>{
        return repository.getFav(id)
    }
}