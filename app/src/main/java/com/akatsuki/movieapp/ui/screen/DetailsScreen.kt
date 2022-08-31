package com.akatsuki.movieapp.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akatsuki.movieapp.R
import com.akatsuki.movieapp.ViewModel.DetailViewModel
import com.akatsuki.movieapp.ViewModel.FavViewModel
import com.akatsuki.movieapp.models.local.SaveModel
import com.akatsuki.movieapp.models.remote.detail.DetailResponse
import com.akatsuki.movieapp.ui.navigation.bottomNaviation.nav_items
import com.akatsuki.movieapp.ui.theme.backgroundA

@Composable
fun DetailsScreen(id: Int, vm: DetailViewModel, nav: NavHostController, favViewModel: FavViewModel){
    Log.e("act", id.toString())

    val result = vm.result.value
    vm.getDetails(id)
    if (result != null){
        Log.e("act", result.actors.toString())

        DetailsScreenUi(id = id, result = result, nav, favViewModel)
    }
}
@Composable
fun DetailsScreenUi(id: Int, result: DetailResponse, nav: NavHostController, favViewModel: FavViewModel){
    val save = remember {
        mutableStateOf(false)
    }

    val savelist = favViewModel.get_fav(result.id!!).observeAsState()

    if (savelist.value != null){
        save.value = true
    }else{
        save.value = false
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = backgroundA)) {

        Box(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = backgroundA)) {

            val url = result.poster?.replace("http://", "h" + "https:/")
            val req = ImageRequest.Builder(context = LocalContext.current)

                .data(url.toString())
                .build()

            AsyncImage(
                model = req,
                contentDescription = "profile ima ge",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .padding(top = 10.dp)
                    .align(Alignment.TopCenter)
                    .graphicsLayer { alpha = 0.78f }
                    .drawWithContent {
                        val colors = listOf(
                            Color.Black,
                            Color.Transparent
                        )
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(colors),
                            blendMode = BlendMode.DstIn
                        )
                    }
            )


            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(alignment = Alignment.TopCenter)){
                Spacer(modifier = Modifier.height(12.dp))
                Image(painterResource(id = R.drawable.ic_back) , contentDescription = "add to fav", modifier = Modifier
                    .size(40.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .clickable(true, onClick = {
                        nav.navigate(nav_items.Ranked.screen_route) {
                            popUpTo(nav_items.Detail.screen_route) { inclusive = true }
                        }
                    })

                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                if (save.value){
                    Image(painterResource(id = R.drawable.save2) , contentDescription = "add to fav", colorFilter = ColorFilter.tint(
                        Color.White),modifier = Modifier
                        .size(40.dp)
                        .padding(end = 10.dp)
                        .clickable(true, onClick = {
                            save.value = !save.value
                            favViewModel.deleteFav(result.id)
                        })

                    )
                }else{
                    Image(painterResource(id = R.drawable.save1) , contentDescription = "add to fav",colorFilter = ColorFilter.tint(
                        Color.White), modifier = Modifier
                        .size(40.dp)
                        .padding(end = 10.dp)
                        .clickable(true, onClick = {
                            save.value = !save.value
                            val i = SaveModel(id = result.id, title = result.title.toString(), poster = result.poster.toString(), imdb = result.imdbId.toString(), year = result.year.toString(), country = result.country.toString())
                            favViewModel.addFav(i)
                        })

                    )
                }

            }


            Row(
                Modifier
                    .padding(start = 40.dp, top = 140.dp)
                    .align(Alignment.TopStart)
                    .wrapContentHeight()
                    .wrapContentWidth()) {

                val pos = ImageRequest.Builder(context = LocalContext.current)
                    .data(url.toString())
                    .build()

                AsyncImage(
                    model = pos,
                    contentDescription = "poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(110.dp)
                        .height(150.dp)
                        .clip(shape = RoundedCornerShape(10.dp))

                )

                Column(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(start = 20.dp, top = 50.dp)){
                    Text(text = result.title.toString(), color = Color.White, fontSize = 14.sp, style = MaterialTheme.typography.h2
                        ,modifier = Modifier.padding(start = 5.dp))

                    Row {
                        Text(text = "Imdb:", color = Color.White, fontSize = 13.sp, style = MaterialTheme.typography.h2
                            ,modifier = Modifier.padding(start = 10.dp))
                        Text(text = result.imdbRating.toString(), color = Color.Yellow, fontSize = 15.sp, style = MaterialTheme.typography.subtitle1
                            ,modifier = Modifier.padding(start = 7.dp))
                    }

                    Text(text = result.actors.toString(), color = Color.White, fontSize = 12.sp, style = MaterialTheme.typography.h2
                        ,modifier = Modifier.padding(start = 5.dp))

                }
            }




        }
        Spacer(modifier = Modifier.height(45.dp))

        Text(text = result.plot.toString(), color = Color.White, fontSize = 14.sp, style = MaterialTheme.typography.h2
            ,modifier = Modifier.padding(start = 10.dp))

        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Text(text = "Writer:", color = Color.White, fontSize = 14.sp, style = MaterialTheme.typography.h2
                ,modifier = Modifier.padding(start = 10.dp))
            Text(text = result.writer.toString(), color = Color.Yellow, fontSize = 12.sp, style = MaterialTheme.typography.subtitle1
                ,modifier = Modifier.padding(start = 7.dp))
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Text(text = "actors:", color = Color.White, fontSize = 14.sp, style = MaterialTheme.typography.h2
                ,modifier = Modifier.padding(start = 10.dp))
            Text(text = result.actors.toString(), color = Color.Yellow, fontSize = 12.sp, style = MaterialTheme.typography.subtitle1
                ,modifier = Modifier.padding(start = 7.dp))
        }

        Spacer(modifier = Modifier.height(25.dp))



        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {

            itemsIndexed(result.images!!) { i, item ->
                Log.e("image", item.toString())
                val sc = item.replace("http://", "https://")
                val req = ImageRequest.Builder(context = LocalContext.current)
                    .data(sc.toString())
                    .build()

                AsyncImage(
                    model = req,
                    contentDescription = "profile ima ge",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(270.dp)
                        .height(150.dp)
                        .padding(start = 25.dp, end = 10.dp)
                        .clip(shape = RoundedCornerShape(7.dp))

                )

            }
        }


    }

}