package com.example.spacexlaunches.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

/**
 * BaseFragment that defines default behaviour of our fragments that will contain DataBinding components
 *
 * @param D Template class for the DataBinding implementation object
 *
 * @author Johnnylee Rocha
 */
abstract class BaseFragment<D : ViewDataBinding> : Fragment(), LifecycleOwner {
    protected lateinit var binding: D

    @LayoutRes
    protected abstract fun layoutRes(): Int


    protected fun fragmentTag(): String = javaClass.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Setup databinding and inflates it
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        binding.lifecycleOwner = this

        return binding.root
    }
}