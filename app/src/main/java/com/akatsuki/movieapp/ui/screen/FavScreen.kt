package com.akatsuki.movieapp.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Lifecycling
import androidx.lifecycle.asFlow
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akatsuki.movieapp.ViewModel.FavViewModel
import com.akatsuki.movieapp.ViewModel.RankViewModel
import com.akatsuki.movieapp.models.local.SaveModel
import com.akatsuki.movieapp.models.local.UsersModel
import com.akatsuki.movieapp.models.remote.Ranked.Data
import com.akatsuki.movieapp.ui.components.ShimmerRanked
import com.akatsuki.movieapp.ui.navigation.bottomNaviation.nav_items
import com.akatsuki.movieapp.ui.theme.backgroundA
import com.akatsuki.movieapp.ui.theme.backgroundC
import com.akatsuki.movieapp.ui.theme.tb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow


@ExperimentalComposeUiApi
@Composable
fun FavScreen(vm: FavViewModel, nav: NavHostController){

    FaveScreenSmall(vm = vm, nav = nav)

}

@ExperimentalComposeUiApi
@Composable
fun FaveScreenSmall(vm: FavViewModel, nav: NavHostController){



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundA)

    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "Favorite Movies",
                color = Color.White,
                fontSize = 22.sp,
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
            )


            Spacer(modifier = Modifier.width(20.dp))

        }
        Log.e("sis", "sis")


        Spacer(modifier = Modifier.height(40.dp))
        val _res = vm.favList.observeAsState()
        val res = _res.value
        Log.e("sis", res?.last()?.poster.toString())

        Log.e("sis", res?.size.toString())
        if (res.isNullOrEmpty()) {
            Text(text = "empty list", modifier = Modifier.align(alignment = Alignment.CenterHorizontally), color = Color.White, fontSize = 40.sp)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(res) { item ->
                    Favitem(item, nav)

                }

            }
        }
    }
}


@Composable
internal fun Favitem(item: SaveModel, nav: NavHostController){
    val id = item.id
    Spacer(modifier = Modifier.height(25.dp))
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(start = 15.dp, end = 15.dp)
        .background(color = Color.Transparent)) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
                .background(color = tb, shape = RoundedCornerShape(9.dp))
                .clickable(true, onClick = {
                    Log.e("clicke rank", "true")
                    nav.navigate(nav_items.Detail.screen_route.plus("/$id")) {
                        popUpTo(nav_items.Ranked.screen_route) {
                            inclusive = true
                        }
                    }
                })
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            //Spacer(modifier = Modifier.width(50.dp))
            Text(text = item.title.toString(), color = Color.White, fontSize = 19.sp, style = MaterialTheme.typography.h1
                ,modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 170.dp)
                    ,
                textAlign = TextAlign.Start,

            )

                    
            Spacer(modifier = Modifier.height(30.dp))


            Row(
                Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterHorizontally)) {

                Text(text = "Imdb:", color = Color.White, fontSize = 13.sp, style = MaterialTheme.typography.h2
                    ,modifier = Modifier.padding(start = 10.dp))
                Text(text = item.imdb.toString(), color = Color.Yellow, fontSize = 15.sp, style = MaterialTheme.typography.subtitle1
                    ,modifier = Modifier.padding(start = 7.dp))
            }

            Spacer(modifier = Modifier.height(7.dp))



            Row(
                Modifier
                    .padding(start = 20.dp)
                    .align(Alignment.CenterHorizontally)) {
                Text(text = item.year.toString(), color = Color.White, fontSize = 13.sp, style = MaterialTheme.typography.h2
                    ,modifier = Modifier.padding(start = 10.dp))
                Text(text = item.country.toString(), color = Color.White, fontSize = 15.sp, style = MaterialTheme.typography.subtitle1
                    ,modifier = Modifier.padding(start = 7.dp))
            }
            
            Spacer(modifier = Modifier.height(15.dp))







        }


        val imgu = item.poster.replace("http://", "https://").toString()
        val req = ImageRequest.Builder(context = LocalContext.current)
            .data(imgu)
            .build()

        AsyncImage(
            model = req,
            contentDescription = "profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(130.dp)
                .height(180.dp)
                .align(Alignment.TopStart)
                .padding(start = 20.dp)
                .clip(RoundedCornerShape(7.dp))
        )
    }

}