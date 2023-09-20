package com.example.paging3example.fragments.home_fargment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging3example.databinding.FragmentHomeBinding
import com.example.paging3example.fragments.home_fargment.domain.model.GamesEntity
import com.example.paging3example.fragments.home_fargment.domain.viewmodel.HomeViewModel
import com.example.paging3example.fragments.home_fargment.presentation.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModels()
    private val pagingAdapter by lazy { HomeAdapter(getGamesItemsDiffCallback()) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setUpViews()
    }

    private fun FragmentHomeBinding.setUpViews() {
        lifecycleScope.launch { collectOnPaginatedGamesList() }
    }

    private suspend fun FragmentHomeBinding.collectOnPaginatedGamesList() {
        setUpRecyclerView()
        viewModel.getPaginatedGamesList().collect {
            pagingAdapter.submitData(it)
        }
    }

    private fun FragmentHomeBinding.setUpRecyclerView() {
        gamesRV.layoutManager = LinearLayoutManager(requireContext())
        gamesRV.adapter = pagingAdapter
    }

    private fun getGamesItemsDiffCallback() = object : DiffUtil.ItemCallback<GamesEntity>() {
        override fun areItemsTheSame(
            oldItem: GamesEntity,
            newItem: GamesEntity
        ) = oldItem.id == newItem.id


        override fun areContentsTheSame(
            oldItem: GamesEntity,
            newItem: GamesEntity
        ) = oldItem == newItem
    }
}