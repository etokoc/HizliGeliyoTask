package com.etoertugrul.hizligeliyotask.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.etoertugrul.hizligeliyotask.R
import com.etoertugrul.hizligeliyotask.adapters.CustomRecylerViewAdapter
import com.etoertugrul.hizligeliyotask.models.ProductResponse
import com.etoertugrul.hizligeliyotask.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getData()
        }
        viewModel.products.observe(viewLifecycleOwner) { userList ->
            initRecylerView(userList.body())
        }
    }

    private fun initRecylerView(productResponseItem: ProductResponse?) {
        val recylerview: RecyclerView = requireView().findViewById(R.id.recylerview_products)
        recylerview.layoutManager = LinearLayoutManager(requireContext())
        recylerview.adapter = productResponseItem?.let { CustomRecylerViewAdapter(it) }
    }

}