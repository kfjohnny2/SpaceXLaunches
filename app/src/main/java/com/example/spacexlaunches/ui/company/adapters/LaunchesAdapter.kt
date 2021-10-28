package com.example.spacexlaunches.ui.company.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexlaunches.R
import com.example.spacexlaunches.databinding.ItemLaunchBinding
import com.example.spacexlaunches.model.Launch
import com.example.spacexlaunches.model.Links

class LaunchesAdapter(private var launches : MutableList<Launch>,private val listener: LaunchAdapterListener) : RecyclerView.Adapter<LaunchesAdapter.LaunchesViewHolder>() {
    interface LaunchAdapterListener {
        fun onLaunchClicked(cardView: View, links: Links)
    }

    class LaunchesViewHolder(private val binding: ItemLaunchBinding, listener: LaunchAdapterListener) : RecyclerView.ViewHolder(binding.root) {
        private val launchViewModel = LaunchViewModel()

        init {
            binding.run {
                this.listener = listener
            }
        }

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

        return LaunchesViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: LaunchesViewHolder, position: Int) {
        holder.bind(launches[position])
    }

    override fun getItemCount() = launches.size
}