package com.example.spacexlaunches.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.spacexlaunches.R
import com.example.spacexlaunches.model.Filters
import com.example.spacexlaunches.utils.enum.SortType
import kotlinx.android.synthetic.main.filters_fragment.*

class FiltersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filters_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bt_apply_filter.setOnClickListener {
            with(FiltersFragmentDirections.actionFiltersFragmentToCompanyLaunchesFragment()) {
                this.filters = applyFilters()
                view.findNavController().navigate(this)
            }
        }
    }

    private fun applyFilters () : Filters{
        return Filters().apply {
            if(!et_year.text.isNullOrBlank()){
                this.year = et_year.text.toString().toInt()
            }
            this.wasSuccess = cb_was_success.isChecked
            when(rg_sort.checkedRadioButtonId){
                R.id.rb_asc -> this.sortType = SortType.ASC
                R.id.rb_desc -> this.sortType = SortType.DESC
            }
        }

    }
}