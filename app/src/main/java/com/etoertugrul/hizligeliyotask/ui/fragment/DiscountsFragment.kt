package com.etoertugrul.hizligeliyotask.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.etoertugrul.hizligeliyotask.databinding.FragmentDiscountsBinding
import com.etoertugrul.hizligeliyotask.ui.viewmodel.DiscountsViewModel

class DiscountsFragment : Fragment() {

    private lateinit var binding: FragmentDiscountsBinding
    private lateinit var viewModel: DiscountsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiscountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiscountsViewModel::class.java)
    }

}