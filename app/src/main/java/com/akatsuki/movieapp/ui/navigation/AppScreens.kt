package com.akatsuki.movieapp.ui.navigation

import java.lang.IllegalArgumentException


enum class AppScreens{
    MainScreen;
    companion object{
        fun fromRoute(route: String?): AppScreens
        = when (route?.substringBefore("/")){
            MainScreen.name -> MainScreen

            null -> MainScreen
            else -> throw IllegalArgumentException("we have error $route")
        }
    }

}