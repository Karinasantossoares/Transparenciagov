package com.project.transparenciagov.core.ext

import android.graphics.Color
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.project.transparenciagov.R
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String) {
    if (url.isEmpty()) {
        setImageResource(R.drawable.ic_person)
        return
    }
    Picasso.get()
        .load(url)
        .networkPolicy(NetworkPolicy.OFFLINE)
        .error(R.drawable.ic_person)
        .into(this@loadImage)

}
