package com.akatsuki.movieapp.utils

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import com.akatsuki.movieapp.R

class alert_error
    (context: Context): AlertDialog(context) {
    @SuppressLint("ResourceType")


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_error)
        this.window?.setGravity(Gravity.BOTTOM)
        this.window?.attributes?.windowAnimations = R.style.Animation
        this.window?.setBackgroundDrawableResource(android.R.color.transparent)
        this.setCancelable(true)



    }
}