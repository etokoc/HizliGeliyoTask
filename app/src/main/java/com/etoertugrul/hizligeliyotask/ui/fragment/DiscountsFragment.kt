package com.etoertugrul.hizligeliyotask.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.etoertugrul.hizligeliyotask.R
import com.etoertugrul.hizligeliyotask.ui.viewmodel.DiscountsViewModel

class DiscountsFragment : Fragment() {

    companion object {
        fun newInstance() = DiscountsFragment()
    }

    private lateinit var viewModel: DiscountsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discounts, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiscountsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}