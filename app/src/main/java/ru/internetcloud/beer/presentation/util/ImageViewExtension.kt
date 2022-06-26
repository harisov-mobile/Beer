package ru.internetcloud.beer.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.internetcloud.beer.R

fun ImageView.loadImage(imageUri: String) {

    Glide.with(context)
        .load(imageUri)
        .placeholder(R.drawable.ic_image)
        .into(this)
}