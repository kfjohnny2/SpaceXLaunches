package com.example.spacexlaunches.ui.company

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexlaunches.R
import com.example.spacexlaunches.base.BaseFragment
import com.example.spacexlaunches.databinding.FragmentCompanyLaunchesBinding
import com.example.spacexlaunches.ui.company.adapters.LaunchesAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [CompanyLaunchesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CompanyLaunchesFragment : BaseFragment<FragmentCompanyLaunchesBinding>() {
    override fun layoutRes() = R.layout.fragment_company_launches

    private val companyLaunchesViewModel by viewModels<CompanyLaunchesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initObservers()

        binding.companyLaunchesViewModel = companyLaunchesViewModel

        return binding.root
    }

    private fun initObservers() {
        with(companyLaunchesViewModel){
            companyData.observe(viewLifecycleOwner, {
                Log.i(fragmentTag(), it.toString())
            })

            launchesList.observe(viewLifecycleOwner, {
                Log.i(fragmentTag(), it.toString())
                with(binding.rvLaunches) {
                    adapter = LaunchesAdapter(it.toMutableList())
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                    setHasFixedSize(true)
                }
            })
        }
    }

}