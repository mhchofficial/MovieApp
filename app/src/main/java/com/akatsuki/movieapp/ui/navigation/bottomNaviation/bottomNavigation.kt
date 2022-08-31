package com.example.compose_ui.navigation.bottomNaviation

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.akatsuki.movieapp.ui.navigation.bottomNaviation.nav_items
import com.akatsuki.movieapp.ui.theme.ClearRippleTheme
import com.akatsuki.movieapp.ui.theme.btn
import com.akatsuki.movieapp.ui.theme.selected
import com.akatsuki.movieapp.ui.theme.tb


@Composable
fun bottomNavigation(navController: NavController) {
    val items = listOf(
        nav_items.Home,
        nav_items.Profile,
        nav_items.Ranked,
        nav_items.Fav

    )
    CompositionLocalProvider(
        LocalRippleTheme provides ClearRippleTheme

    ) {
        BottomNavigation(
            backgroundColor = tb,
            modifier = Modifier.clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                //.border(BorderStroke(width = 1.dp,light_blue))
        )
            {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = item.icon), contentDescription = item.title, modifier = Modifier.size(25.dp)) },
                    label = { Text(fontSize = 12.sp,
                        text = item.title) },

                    selectedContentColor = btn,
                    unselectedContentColor = Color.White,
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

        }
    }

}