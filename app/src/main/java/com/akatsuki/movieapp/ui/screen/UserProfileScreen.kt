package com.akatsuki.movieapp.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.akatsuki.movieapp.R
import com.akatsuki.movieapp.ViewModel.UserViewModel
import com.akatsuki.movieapp.ui.theme.backgroundA
import com.akatsuki.movieapp.ui.theme.btn


@Composable
fun UserProfileScreen(vm: UserViewModel){
    val _users = vm.userList.observeAsState()
    val users = _users.value?.last()
    


    val save = remember {
        mutableStateOf(false)
    }

    val exit = remember {
        mutableStateOf(false)
    }


    if (exit.value){
        vm.deleteUser()
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = backgroundA)) {
        val req = ImageRequest.Builder(context = LocalContext.current)
            .data(users?.image.toString())
            .build()

        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage(
            model = req,
            contentDescription = "Profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                //.padding(top = 35.dp)
                .clip(shape = CircleShape)
                .align(Alignment.CenterHorizontally)


        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = users?.fullname.toString(), color = Color.White, fontSize = 14.sp, style = MaterialTheme.typography.h2
            ,modifier = Modifier
                .padding(start = 10.dp)
                .align(Alignment.CenterHorizontally))
        
        Spacer(modifier = Modifier.height(60.dp))

        Button(onClick = {save.value = true},modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .align(alignment = Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = btn)

        ) {
            Image(
                painterResource(id = R.drawable.save),
                contentDescription ="save",
                modifier = Modifier.size(20.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Spacer(modifier = Modifier.width(5.dp))
            
            Text(text = "Saved", color = Color.White)
        }

        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = {exit.value = true},modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .align(alignment = Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = btn)

        ) {
            Image(
                painterResource(id = R.drawable.logout),
                contentDescription ="Logout",
                modifier = Modifier.size(20.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Spacer(modifier = Modifier.width(5.dp))

            Text(text = "Logout", color = Color.White)
        }




    }

}