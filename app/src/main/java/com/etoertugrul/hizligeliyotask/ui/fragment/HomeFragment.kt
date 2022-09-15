package com.etoertugrul.hizligeliyotask.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.etoertugrul.hizligeliyotask.adapters.CustomRecylerViewAdapter
import com.etoertugrul.hizligeliyotask.databinding.FragmentHomeBinding
import com.etoertugrul.hizligeliyotask.models.ProductResponse
import com.etoertugrul.hizligeliyotask.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

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
        val recyclerView: RecyclerView = binding.recylerviewProducts
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = productResponseItem?.let { CustomRecylerViewAdapter(requireContext(),it) }
    }

}