package com.mfamstory.gituser.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mfamstory.gituser.R
import com.mfamstory.gituser.databinding.ItemSearchBinding
import com.mfamstory.gituser.network.model.User
import com.mfamstory.gituser.ui.viewmodel.SearchViewModel

class UserAdapter (var items : List<User>, val vm : SearchViewModel) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.apply {
            binding.vm = vm
            binding.item = items[position]
        }
    }

    class UserViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemSearchBinding>(view)!!
    }
}