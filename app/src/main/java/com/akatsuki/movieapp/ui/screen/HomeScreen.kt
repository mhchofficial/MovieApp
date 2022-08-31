package com.akatsuki.movieapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akatsuki.movieapp.ViewModel.ApiHomeViewModel
import com.akatsuki.movieapp.models.remote.TopResponse.Result
import com.akatsuki.movieapp.ui.components.ShimmerHome
import com.akatsuki.movieapp.ui.theme.backgroundA
import com.akatsuki.movieapp.ui.theme.tb
import com.akatsuki.movieapp.utils.Base.TOP_URL
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay


@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(vm: ApiHomeViewModel) {


    //ust for showing shimmer effect
    if (!vm.loading.value) {
        ShimmerHome()
        LaunchedEffect(key1 = Unit){
            delay(3000)
            vm.loading.value = true
        }
    }
    else{
        HomeScreenShow(vm = vm)
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreenShow(vm: ApiHomeViewModel){




    vm.getUpdated("25")
    val up = vm.upresult.value

    vm.getPop(24)
    val pop = vm.popresult.value


    //i will add some
    vm.getTop(TOP_URL)
    val top = vm.Topresult.value



















    //apiViewModel.loadPopData(24)
    // val pop by apiViewModel.pop.observeAsState()




    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) { scrollState.animateScrollTo(10000) }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .background(color = backgroundA)

    ) {

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Movies", color = Color.White, fontSize = 19.sp, style = MaterialTheme.typography.h1
            ,modifier = Modifier.padding(start = 10.dp))
        Spacer(modifier = Modifier.height(10.dp))

        val pagerState = rememberPagerState()

        if (!top.isNullOrEmpty()){


            HorizontalPager(
                count = top?.size!!,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 12.dp),
                modifier = Modifier
                    //.weight(1f)
                    .fillMaxWidth()
                    .height(200.dp),
            ) { page ->
                PagerSampleItem(
                    page = page,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(top = 20.dp)
                        .padding(horizontal = 50.dp)
                        .aspectRatio(1f),
                    top = top!!
                )
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(4.dp)
            ,
            activeColor = tb,
            inactiveColor = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Popular", color = Color.White, fontSize = 19.sp, style = MaterialTheme.typography.h1
            ,modifier = Modifier.padding(start = 10.dp))

        Spacer(modifier = Modifier.height(10.dp))


        if (!pop.isNullOrEmpty()){
            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()){
                itemsIndexed(pop) { i, item ->
                    Column(modifier = Modifier
                        .width(140.dp)
                        .height(255.dp)
                        .padding(start = 10.dp, end = 10.dp)) {
                        val urlp = item.poster?.replace("http://", "https://")
                        val req = ImageRequest.Builder(context = LocalContext.current)
                            .data(urlp)
                            .build()

                        AsyncImage(
                            model = req,
                            contentDescription = "POP POSTERS",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )

                        Text(text = item.title.toString(), color = Color.White, fontSize = 14.sp, style = MaterialTheme.typography.h2
                            ,modifier = Modifier.padding(start = 10.dp))
                        Spacer(modifier = Modifier.height(4.dp))

                        Row() {
                            Text(text = "Imdb:", color = Color.White, fontSize = 13.sp, style = MaterialTheme.typography.h2
                                ,modifier = Modifier.padding(start = 10.dp))
                            Text(text = item.imdbRating.toString(), color = Color.Yellow, fontSize = 15.sp, style = MaterialTheme.typography.subtitle1
                                ,modifier = Modifier.padding(start = 7.dp))
                        }
                    }


                }
            }
        }


        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Updated", color = Color.White, fontSize = 19.sp, style = MaterialTheme.typography.h1
            ,modifier = Modifier.padding(start = 10.dp))

        Spacer(modifier = Modifier.height(10.dp))

        if (!up.isNullOrEmpty()){
            //val itemsP: List<Data> = up?.data!!
            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {
                itemsIndexed(up) { i, item ->

                    Column(
                        modifier = Modifier
                            .width(140.dp)
                            .height(255.dp)
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        val url = item.poster?.replace("http://", "https://")
                        val req = ImageRequest.Builder(context = LocalContext.current)
                            .data(url)
                            .build()

                        AsyncImage(
                            model = req,
                            contentDescription = "updated poster",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )

                        Text(
                            text = item.title.toString(),
                            color = Color.White,
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.padding(start = 10.dp)
                        )


                        Spacer(modifier = Modifier.height(4.dp))

                        Row() {
                            Text(
                                text = "Imdb:",
                                color = Color.White,
                                fontSize = 13.sp,
                                style = MaterialTheme.typography.h2,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                            Text(
                                text = item.imdbRating.toString(),
                                color = Color.Yellow,
                                fontSize = 15.sp,
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(start = 7.dp)
                            )
                        }
                    }

                }

            }
        }



    }





}









@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun PagerSampleItem(
    page: Int,
    modifier: Modifier = Modifier,
    top: List<Result>,
) {
    val req = ImageRequest.Builder(context = LocalContext.current)
        .data(top[page].poster)
        .build()

    AsyncImage(
        model = req,
        contentDescription = "profile image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )
}



