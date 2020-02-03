package com.mfamstory.gituser.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mfamstory.gituser.R
import com.mfamstory.gituser.database.entity.LikeUser
import com.mfamstory.gituser.databinding.ItemLikeUserBinding
import com.mfamstory.gituser.ui.viewmodel.LikeViewModel

class LikeUserAdapter(val vm: LikeViewModel) : PagedListAdapter<LikeUser, LikeUserAdapter.LikeUserViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeUserViewHolder {
        return LikeUserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_like_user, parent,false))
    }

    override fun onBindViewHolder(holder: LikeUserViewHolder, position: Int) {
        getItem(position)?.run {
            holder.binding.item = this
            holder.binding.vm = vm
        }
    }

    class LikeUserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemLikeUserBinding>(view)!!
    }

}

private val callback = object : DiffUtil.ItemCallback<LikeUser>() {
    override fun areItemsTheSame(oldItem: LikeUser, newItem: LikeUser) = oldItem.login == newItem.login
    override fun areContentsTheSame(oldItem: LikeUser, newItem: LikeUser) = oldItem == newItem
}