package com.mfamstory.gituser.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.mfamstory.gituser.database.entity.LikeUser
import com.mfamstory.gituser.ui.viewmodel.LikeViewModel

@BindingAdapter(value = ["likeUsers", "viewModel"])
fun setLikeUserAdapter(view: RecyclerView, items: PagedList<LikeUser>?, vm: LikeViewModel) {
    view.adapter?.run {
        if (this is LikeUserAdapter) this.submitList(items)
    } ?: run {
        LikeUserAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}