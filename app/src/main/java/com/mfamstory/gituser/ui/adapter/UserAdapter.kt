package com.mfamstory.gituser.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mfamstory.gituser.R
import com.mfamstory.gituser.databinding.ItemSearchBinding
import com.mfamstory.gituser.network.model.User
import com.mfamstory.gituser.ui.viewmodel.SearchViewModel

class UserAdapter(val vm : SearchViewModel) :
    PagedListAdapter<User, UserAdapter.UserViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.apply {
            binding.vm = vm
            binding.item = getItem(position)
        }
    }

    inner class UserViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemSearchBinding>(view)!!
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.login == newItem.login
            override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
        }
    }
}

