package com.akatsuki.movieapp.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akatsuki.movieapp.ViewModel.DetailViewModel
import com.akatsuki.movieapp.models.remote.detail.DetailResponse
import com.akatsuki.movieapp.ui.theme.backgroundA

@Composable
fun DetailsScreen(id: Int, vm: DetailViewModel){
    Log.e("act", id.toString())

    val result = vm.result.value
    vm.getDetails(id)
    if (result != null){
        Log.e("act", result.actors.toString())

        DetailsScreenUi(id = id, result = result)
    }
}
@Composable
fun DetailsScreenUi(id: Int, result: DetailResponse){

    Column(
        Modifier
            .fillMaxSize()
            .background(color = backgroundA)) {

        Box(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = backgroundA)) {

            val url = result?.poster?.replace("http://", "h" + "https:/")
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
                    Text(text = result?.title.toString(), color = Color.White, fontSize = 14.sp, style = MaterialTheme.typography.h2
                        ,modifier = Modifier.padding(start = 5.dp))

                    Row() {
                        Text(text = "Imdb:", color = Color.White, fontSize = 13.sp, style = MaterialTheme.typography.h2
                            ,modifier = Modifier.padding(start = 10.dp))
                        Text(text = result?.imdbRating.toString(), color = Color.Yellow, fontSize = 15.sp, style = MaterialTheme.typography.subtitle1
                            ,modifier = Modifier.padding(start = 7.dp))
                    }

                    Text(text = result?.actors.toString(), color = Color.White, fontSize = 12.sp, style = MaterialTheme.typography.h2
                        ,modifier = Modifier.padding(start = 5.dp))

                }
            }




        }
        Spacer(modifier = Modifier.height(45.dp))

        Text(text = result?.plot.toString(), color = Color.White, fontSize = 14.sp, style = MaterialTheme.typography.h2
            ,modifier = Modifier.padding(start = 10.dp))

        Spacer(modifier = Modifier.height(15.dp))
        Row() {
            Text(text = "Writer:", color = Color.White, fontSize = 14.sp, style = MaterialTheme.typography.h2
                ,modifier = Modifier.padding(start = 10.dp))
            Text(text = result?.writer.toString(), color = Color.Yellow, fontSize = 12.sp, style = MaterialTheme.typography.subtitle1
                ,modifier = Modifier.padding(start = 7.dp))
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row() {
            Text(text = "actors:", color = Color.White, fontSize = 14.sp, style = MaterialTheme.typography.h2
                ,modifier = Modifier.padding(start = 10.dp))
            Text(text = result?.actors.toString(), color = Color.Yellow, fontSize = 12.sp, style = MaterialTheme.typography.subtitle1
                ,modifier = Modifier.padding(start = 7.dp))
        }

        Spacer(modifier = Modifier.height(25.dp))



        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {

            itemsIndexed(result?.images!!) { i, item ->
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