package com.akatsuki.movieapp.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akatsuki.movieapp.R
import com.akatsuki.movieapp.ViewModel.UserViewModel
import com.akatsuki.movieapp.models.local.UsersModel
import com.akatsuki.movieapp.ui.navigation.bottomNaviation.nav_items
import com.akatsuki.movieapp.ui.theme.backgroundA
import com.akatsuki.movieapp.ui.theme.backgroundC
import com.akatsuki.movieapp.ui.theme.btn


@Composable
fun UsersStateScreen(vm: UserViewModel, navController: NavHostController){
    //LoginScreen(vm = vm)
    val users by vm.userList.observeAsState()
    Log.e("sis", users?.size.toString())
    if (users.isNullOrEmpty()){
        navController.navigate(nav_items.Login.screen_route){
            popUpTo(nav_items.Profile.screen_route)
        }
    }

    /*
    when(users){
        null -> {
            navController.navigate(nav_items.Login.screen_route){
                popUpTo(nav_items.Profile.screen_route)
            }
        }
        else -> {
            navController.navigate(nav_items.User.screen_route){
                popUpTo(nav_items.Profile.screen_route)
            }

        }
    }

     */




    



}