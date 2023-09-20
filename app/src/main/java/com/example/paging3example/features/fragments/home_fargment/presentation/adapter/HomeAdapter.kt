package com.example.paging3example.features.fragments.home_fargment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3example.core.Utils
import com.example.paging3example.databinding.ItemGamesBinding
import com.example.paging3example.features.fragments.home_fargment.domain.model.GamesEntity

class HomeAdapter(diffUtil: DiffUtil.ItemCallback<GamesEntity>) :
    PagingDataAdapter<GamesEntity, HomeAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }


    inner class ViewHolder(private val binding: ItemGamesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: GamesEntity) {
            binding.setUpViews(item)
        }

        private fun ItemGamesBinding.setUpViews(item: GamesEntity) {
            gamesTitleTv.text = item.fullName
            dateTv.text = Utils.decodeDateString(item.date, "yyyy-MM-dd")
        }
    }
}