package com.akatsuki.movieapp.ViewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.akatsuki.movieapp.models.local.UsersModel
import com.akatsuki.movieapp.models.remote.Response.Data
import com.akatsuki.movieapp.models.remote.login.LoginResponse
import com.akatsuki.movieapp.repository.DataRepository
import com.akatsuki.movieapp.utils.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: DataRepository
): ViewModel() {
    val userList: LiveData<List<UsersModel>> = repository.getAllusers().asLiveData()

    //we don't needed but maybe you want to know how works
   /*fun get_user(id: Int): LiveData<UsersModel>{
        return repository.getUser(id)
    }

    */

    fun addUser(item: UsersModel) {
        viewModelScope.launch {
            repository.adduser(item)
        }
    }

    fun deleteUser(){
        viewModelScope.launch {
            repository.deleteusers()
        }
    }

    var _result: MutableLiveData<LoginResponse> = MutableLiveData()
    val result: LiveData<LoginResponse> = _result
    val loading = mutableStateOf(false)

    fun getLogin(url: String){
        viewModelScope.launch {
            //bc api to fast and we want to see progressbar
            delay(2000)
            try {
                val newResult = repository.getLogin(url)
                if (newResult is Resource.Success) {
                    _result.value = newResult.data!!
                }
                loading.value = true


            }catch (ex : Exception){
                Log.e("error", ex.message.toString())
            }
        }

    }
}