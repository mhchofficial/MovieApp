package com.akatsuki.movieapp

import android.R.color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.akatsuki.movieapp.ui.navigation.AppNavigation
import com.akatsuki.movieapp.ui.theme.MovieAppTheme
import com.akatsuki.movieapp.ui.theme.backgroundA
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                Surface(color = backgroundA,
                modifier = Modifier.fillMaxSize()) {
                    
                }
                AppNavigation()

            }
        }
    }

}


