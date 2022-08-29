package com.akatsuki.movieapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.akatsuki.movieapp.ViewModel.ApiHomeViewModel
import com.akatsuki.movieapp.ViewModel.DetailViewModel
import com.akatsuki.movieapp.ViewModel.RankViewModel
import com.akatsuki.movieapp.ViewModel.UserViewModel
import com.akatsuki.movieapp.ui.navigation.bottomNaviation.BottomNaviationController
import com.example.compose_ui.navigation.bottomNaviation.bottomNavigation

@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainScreen(vm: ApiHomeViewModel = viewModel(), rankViewModel: RankViewModel = viewModel(), userViewModel: UserViewModel = viewModel(), detailViewModel: DetailViewModel = viewModel()){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { bottomNavigation(navController = navController) }
    ) {
        BottomNaviationController(navController = navController, vm = vm, rankViewModel = rankViewModel, userViewModel = userViewModel, detailViewModel = detailViewModel)

    }

}