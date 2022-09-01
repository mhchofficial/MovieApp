package com.akatsuki.movieapp.ui.navigation.bottomNaviation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.akatsuki.movieapp.ViewModel.*
import com.akatsuki.movieapp.ui.screen.*
import com.google.gson.Gson


@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun BottomNaviationController(navController: NavHostController, vm: ApiHomeViewModel, rankViewModel: RankViewModel, userViewModel: UserViewModel, detailViewModel: DetailViewModel, favViewModel: FavViewModel) {
    NavHost(navController, startDestination = nav_items.Home.screen_route) {
        //i use this for increase speed for checking
        //and normally we can do tis in profile screen but the speed of checking will be decreased
        val isLogin = mutableStateOf(false)



        composable(nav_items.Profile.screen_route) {

            if (isLogin.value){
                Log.e("profile", "page")
                UserProfileScreen(userViewModel, navController)

            }
            else{
                Log.e("login", "page")
                UserLoginScreen(userViewModel, navController)

            }
            //UsersStateScreen(vm = userViewModel, navController)
        }
        composable(nav_items.Home.screen_route) {
            val users = userViewModel.userList.observeAsState()
            if (!users.value.isNullOrEmpty()){
                isLogin.value = true
            }
            HomeScreen(vm, navController)
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
                DetailsScreen(id = id!!, detailViewModel, navController, favViewModel)
        }


        composable(nav_items.Fav.screen_route) {
                FavScreen(vm = favViewModel, nav = navController)

        }

        /*
        composable(nav_items.User.screen_route) {


        }

         */
    }
}