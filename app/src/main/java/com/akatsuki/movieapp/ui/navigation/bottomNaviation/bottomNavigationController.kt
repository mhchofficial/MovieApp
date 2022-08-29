package com.akatsuki.movieapp.ui.navigation.bottomNaviation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.akatsuki.movieapp.ViewModel.ApiHomeViewModel
import com.akatsuki.movieapp.ViewModel.DetailViewModel
import com.akatsuki.movieapp.ViewModel.RankViewModel
import com.akatsuki.movieapp.ViewModel.UserViewModel
import com.akatsuki.movieapp.ui.screen.*
import com.google.gson.Gson


@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun BottomNaviationController(navController: NavHostController, vm: ApiHomeViewModel, rankViewModel: RankViewModel, userViewModel: UserViewModel, detailViewModel: DetailViewModel) {
    NavHost(navController, startDestination = nav_items.Profile.screen_route) {
        composable(nav_items.Profile.screen_route) {
            val users = userViewModel.userList.observeAsState()
            if (users.value.isNullOrEmpty()){
                Log.e("logn", "page")
                UserLoginScreen(userViewModel)
            }
            else{
                Log.e("profile", "page")
                UserProfileScreen(userViewModel)

            }
            //UsersStateScreen(vm = userViewModel, navController)
        }
        composable(nav_items.Home.screen_route) {
            //val vm: ApiViewModel = viewModel()
            HomeScreen(vm)
        }

        composable(nav_items.Ranked.screen_route) {
            RankingScreen(rankViewModel, navController)
        }




        composable(nav_items.Main.screen_route){
            MainScreen()
        }


        composable(nav_items.Detail.screen_route .plus("/{uid}"),
            arguments = listOf(navArgument("uid") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
                val id =  backStackEntry.arguments?.getInt("uid")
                DetailsScreen(id = id!!, detailViewModel)
        }


        composable(nav_items.Login.screen_route) {


        }

        /*
        composable(nav_items.User.screen_route) {


        }

         */
    }
}