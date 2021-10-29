package com.example.spacexlaunches.ui.company

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexlaunches.R
import com.example.spacexlaunches.base.BaseFragment
import com.example.spacexlaunches.databinding.FragmentCompanyLaunchesBinding
import com.example.spacexlaunches.model.Links
import com.example.spacexlaunches.ui.company.adapters.LaunchesAdapter
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.net.Uri
import android.view.*
import android.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spacexlaunches.utils.helpers.showAlertDialog


/**
 * Fragment for retrieving company info and it's launches
 *
 * @author Johnnylee Rocha
 */
@AndroidEntryPoint
class CompanyLaunchesFragment : BaseFragment<FragmentCompanyLaunchesBinding>(),
    LaunchesAdapter.LaunchAdapterListener {
    override fun layoutRes() = R.layout.fragment_company_launches

    private val companyLaunchesViewModel by viewModels<CompanyLaunchesViewModel>()

    private val args: CompanyLaunchesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        setHasOptionsMenu(true)

        initObservers()

        binding.companyLaunchesViewModel = companyLaunchesViewModel

        return binding.root
    }

    private fun initObservers() {
        with(companyLaunchesViewModel) {

            companyData.observe(viewLifecycleOwner, {
                Log.i(fragmentTag(), it.toString())
            })

            launchesList.observe(viewLifecycleOwner, {
                val filters = args.filters
                if (filters != null) {
                    filterLaunches(filters)
                } else {
                    with(binding.rvLaunches) {
                        adapter = LaunchesAdapter(it.toMutableList(), this@CompanyLaunchesFragment)
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                    }
                }
            })

            filteredLaunchesList.observe(viewLifecycleOwner, {
                if (it != null) {
                    with(binding.rvLaunches) {
                        adapter = LaunchesAdapter(it.toMutableList(), this@CompanyLaunchesFragment)
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                    }
                }
            })

            errorMessage.observe(viewLifecycleOwner, {
                if(!it.isNullOrBlank()){
                    context?.let { ctx -> showAlertDialog(ctx, getString(R.string.generic_error_title), it) }
                }
            })
        }
    }

    override fun onLaunchClicked(cardView: View, links: Links) {
        var browserIntent: Intent? = null
        val popupMenu = PopupMenu(context, binding.rvLaunches)
        popupMenu.menuInflater.inflate(R.menu.option_links, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_article ->
                    browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(links.articleLink))
                R.id.action_wikipedia ->
                    browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(links.wikipedia))
                R.id.action_videos ->
                    browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(links.videoLink))
            }
            startActivity(browserIntent)
            true
        }
        popupMenu.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear()
        inflater.inflate(R.menu.menu_search, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> {
                with(CompanyLaunchesFragmentDirections.actionCompanyLaunchesFragmentToFiltersFragment()) {
                    binding.root.findNavController().navigate(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}