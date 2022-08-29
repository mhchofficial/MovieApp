package com.akatsuki.movieapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.akatsuki.movieapp.ui.components.ShimmerRanked
import com.akatsuki.movieapp.ui.navigation.AppNavigation
import com.akatsuki.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                AppNavigation()
                //ShimmerRanked()

            }
        }
    }

}


