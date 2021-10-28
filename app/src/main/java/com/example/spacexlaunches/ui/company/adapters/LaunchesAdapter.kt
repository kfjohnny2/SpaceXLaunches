package com.example.spacexlaunches.ui.company.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexlaunches.R
import com.example.spacexlaunches.databinding.ItemLaunchBinding
import com.example.spacexlaunches.model.Launch

class LaunchesAdapter(private var launches : MutableList<Launch>) : RecyclerView.Adapter<LaunchesAdapter.LaunchesViewHolder>() {
    class LaunchesViewHolder(private val binding: ItemLaunchBinding) : RecyclerView.ViewHolder(binding.root) {
        private val launchViewModel = LaunchViewModel()
        fun bind(launch: Launch){
            launchViewModel.bind(launch)
            binding.launchViewModel = launchViewModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemLaunchBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_launch, parent, false)

        return LaunchesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchesViewHolder, position: Int) {
        holder.bind(launches[position])
    }

    override fun getItemCount() = launches.size
}