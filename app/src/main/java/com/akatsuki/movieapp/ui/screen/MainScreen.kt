package com.akatsuki.movieapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.akatsuki.movieapp.ViewModel.*
import com.akatsuki.movieapp.ui.navigation.bottomNaviation.BottomNaviationController
import com.akatsuki.movieapp.ui.navigation.bottomNaviation.BottomNavigation
import com.akatsuki.movieapp.ui.theme.backgroundA

@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainScreen(vm: ApiHomeViewModel = viewModel(), rankViewModel: RankViewModel = viewModel(), userViewModel: UserViewModel = viewModel(), detailViewModel: DetailViewModel = viewModel(),favViewModel: FavViewModel = viewModel()){
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.background(color = backgroundA),
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        BottomNaviationController(navController = navController, vm = vm, rankViewModel = rankViewModel, userViewModel = userViewModel, detailViewModel = detailViewModel, favViewModel = favViewModel)

    }

}