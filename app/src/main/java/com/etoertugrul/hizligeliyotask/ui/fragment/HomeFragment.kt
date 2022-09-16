package com.etoertugrul.hizligeliyotask.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.etoertugrul.hizligeliyotask.R
import com.etoertugrul.hizligeliyotask.adapters.ProductRecylerViewAdapter
import com.etoertugrul.hizligeliyotask.databinding.FragmentHomeBinding
import com.etoertugrul.hizligeliyotask.models.ProductResponse
import com.etoertugrul.hizligeliyotask.ui.activity.FilterActivity
import com.etoertugrul.hizligeliyotask.ui.viewmodel.HomeViewModel
import com.etoertugrul.hizligeliyotask.util.Constants
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
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getData()
        }
        observeData()
        return binding.root
    }

    private fun observeData() {
        viewModel.products.observe(viewLifecycleOwner) { userList ->
            initView(userList.body())
        }
    }

    var adapter: ProductRecylerViewAdapter? = null
    private fun initView(productResponseItem: ProductResponse?) {
        binding.apply {
            //StaggeredGridLayout is 2 column add to recylerView.
            recylerviewProducts.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = productResponseItem?.let {
                ProductRecylerViewAdapter(
                    requireContext(),
                    it
                )
            }
            //RecylerView set adapter
            recylerviewProducts.adapter = adapter

            //Search edittext.
            edittextSearch.addTextChangedListener {
                adapter?.filter?.filter(it.toString().trim())
            }

            //filter button
            btnFilter.setOnClickListener {
                val intent = Intent(requireContext(), FilterActivity::class.java)
                intent.putExtra(Constants.INTENT_TAG, viewModel.getCategories())
                startActivityForResult(
                    intent,
                    Constants.ACTIVITY_CODE
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.ACTIVITY_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val tag = data?.getStringExtra(Constants.ACTIVITY_RESULT_TAG)
                binding.edittextSearch.setText(tag)
                adapter?.filter?.filter(tag)
                if (!tag.equals(""))
                    binding.btnFilter.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_filter_badget,0,0,0)
                else
                    binding.btnFilter.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_filter_unbadget,0,0,0)
            }
        }
    }
}