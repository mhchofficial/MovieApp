package com.akatsuki.movieapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akatsuki.movieapp.ViewModel.*
import com.akatsuki.movieapp.ui.screen.MainScreen
import com.akatsuki.movieapp.ui.theme.backgroundA


@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun AppNavigation(vm: ApiHomeViewModel = viewModel(),
                  rankViewModel: RankViewModel = viewModel(),
                  userViewModel: UserViewModel = viewModel(),
                  detailViewModel: DetailViewModel = viewModel(),
                  favViewModel: FavViewModel = viewModel()){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.name,
    modifier = Modifier.background(color = backgroundA)){


        composable(AppScreens.MainScreen.name){
            MainScreen(vm, rankViewModel, userViewModel, detailViewModel, favViewModel)
            //navController = navController
        }



    }
}