package com.mfamstory.gituser.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mfamstory.gituser.network.model.User
import com.mfamstory.gituser.ui.viewmodel.SearchViewModel
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter

@BindingAdapter(value = ["avatarUrl"])
fun setAvatar(view: ImageView, avatarUrl: String) {
    Glide.with(view.context)
        .load(avatarUrl)
        .transition(DrawableTransitionOptions.withCrossFade(500))
        .into(view)
}