package com.akatsuki.movieapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akatsuki.movieapp.ViewModel.ApiHomeViewModel
import com.akatsuki.movieapp.ViewModel.DetailViewModel
import com.akatsuki.movieapp.ViewModel.RankViewModel
import com.akatsuki.movieapp.ViewModel.UserViewModel
import com.akatsuki.movieapp.ui.screen.MainScreen


@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun AppNavigation(vm: ApiHomeViewModel = viewModel(),
                  rankViewModel: RankViewModel = viewModel(),
                  userViewModel: UserViewModel = viewModel(),
                  detailViewModel: DetailViewModel = viewModel()){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.name){


        composable(AppScreens.MainScreen.name){
            MainScreen(vm, rankViewModel, userViewModel, detailViewModel)
            //navController = navController
        }



    }
}