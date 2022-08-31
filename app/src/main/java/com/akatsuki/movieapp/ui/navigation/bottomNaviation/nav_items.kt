package com.akatsuki.movieapp.ui.navigation.bottomNaviation

import com.akatsuki.movieapp.R
import com.akatsuki.movieapp.ui.screen.DetailsScreen


sealed class nav_items(var title:String, var icon: Int, var screen_route:String){
    object Ranked: nav_items("Ranking", R.drawable.rank, "ranking")
    object Home : nav_items("Home", R.drawable.home,"home")
    object Profile: nav_items("Profile",R.drawable.user,"Profile")


    object Login: nav_items("Login", R.drawable.rank, "login")
    //object User: nav_items("User", R.drawable.rank, "user")

    object Detail: nav_items("Detail", R.drawable.rank, "detail")

    object Main: nav_items("Main", R.drawable.rank, "main")


    object Fav: nav_items("Fav", R.drawable.rank, "fav")



}
