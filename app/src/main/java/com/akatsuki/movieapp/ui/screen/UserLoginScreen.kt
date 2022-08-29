package com.akatsuki.movieapp.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akatsuki.movieapp.ViewModel.UserViewModel
import com.akatsuki.movieapp.models.local.UsersModel
import com.akatsuki.movieapp.ui.navigation.bottomNaviation.nav_items
import com.akatsuki.movieapp.ui.theme.backgroundA
import com.akatsuki.movieapp.ui.theme.backgroundC
import com.akatsuki.movieapp.ui.theme.btn


@Composable
fun UserLoginScreen(vm: UserViewModel){
    val email = remember {
        mutableStateOf("")
    }

    val click = remember {
        mutableStateOf(false)
    }

    val password = remember {
        mutableStateOf("")
    }

    val load = remember {
        mutableStateOf(false)
    }

    if (click.value){
        load.value = true
        vm.getLogin("https://bumsun.ir/movie/login.json")
        val _result = vm.result.observeAsState()
        val result = _result.value

        if (result != null){

            Log.e("data", result.toString())
            Log.e("data", result.fullname.toString())
            Log.e("data", result.image.toString())

            val uitem = UsersModel(1, result.fullname.toString(), result.username.toString(), result.image.toString(), result.session.toString())
            vm.addUser(item = uitem)
            load.value = false

        }



    }



    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = backgroundA)) {
        Column(
            modifier = Modifier
                .background(backgroundC, shape = RoundedCornerShape(12.dp))
                .width(300.dp)
                .height(500.dp)
                //.clip(shape = RoundedCornerShape(18.dp))
                .align(alignment = Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login", fontSize = 18.sp, color = Color.White, modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(25.dp))
            TextField(value = email.value
                , onValueChange = {email.value = it}
                , label = { Text(text = "Email", color = Color.White) }
                , modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(10.dp))

                    .align(alignment = Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(15.dp))
            TextField(value = password.value
                , onValueChange = {password.value = it}
                , label = { Text(text = "Password", color = Color.White) }
                , modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Button(onClick = {click.value = true},modifier = Modifier
                .height(40.dp)
                .width(80.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .align(alignment = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = btn)

            ) {
                Text(text = "Login", color = Color.White)
            }

            if (load.value){
                CircularProgressIndicator(modifier = Modifier.size(50.dp).align(alignment = Alignment.CenterHorizontally), color = btn)
            }


        }
    }

}