package com.mfamstory.gituser.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mfamstory.gituser.R
import com.mfamstory.gituser.databinding.FragmentLikeBinding
import com.mfamstory.gituser.ui.adapter.LikeUserAdapter
import com.mfamstory.gituser.ui.viewmodel.LikeViewModel
import com.mfamstory.gituser.ui.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_like.*
import org.koin.android.viewmodel.ext.android.getViewModel

class LikeFragment : BindingFragment<FragmentLikeBinding>() {

    override fun getLayoutResId() = R.layout.fragment_like

    companion object {

        @Volatile private var instance: LikeFragment? = null

        @JvmStatic
        fun getInstance(): LikeFragment = instance ?: synchronized(this) {
            instance ?: LikeFragment().also {
                instance = it
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm : LikeViewModel = getViewModel()
        binding.vm = vm
        binding.lifecycleOwner = this

        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                return makeMovementFlags(0, ItemTouchHelper.LEFT)
            }
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as LikeUserAdapter.LikeUserViewHolder).binding.item?.let {
                    vm.removeUser(it)
                }
            }
        }).attachToRecyclerView(list)
    }
}