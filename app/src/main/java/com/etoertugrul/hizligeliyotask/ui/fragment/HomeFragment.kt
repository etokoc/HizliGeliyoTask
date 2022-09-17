package com.etoertugrul.hizligeliyotask.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.etoertugrul.hizligeliyotask.R
import com.etoertugrul.hizligeliyotask.adapters.ProductRecylerViewAdapter
import com.etoertugrul.hizligeliyotask.databinding.FragmentHomeBinding
import com.etoertugrul.hizligeliyotask.models.ProductResponse
import com.etoertugrul.hizligeliyotask.ui.activity.FilterActivity
import com.etoertugrul.hizligeliyotask.ui.viewmodel.HomeViewModel
import com.etoertugrul.hizligeliyotask.util.Constants
import com.etoertugrul.hizligeliyotask.util.EnumSorted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var productResponse: ProductResponse
    private var sortedProductResponse = ProductResponse()
    private lateinit var enumSortedType: EnumSorted
    private var adapter: ProductRecylerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        //data catch from api with viewModel.
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getData()
        }

        //Observe fun1
        observeData()
        return binding.root
    }


    //Listen to data from get api.(if swiperefresh is open, next to stop)
    private fun observeData() {
        viewModel.products.observe(viewLifecycleOwner) { userList ->
            productResponse = userList.body()!!
            initView(productResponse)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    //initialize views
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

            //sort button
            btnSort.setOnClickListener {
                val popup = PopupMenu(requireContext(), it)
                popup.menuInflater.inflate(R.menu.sorted_menu, popup.menu)
                popup.setOnMenuItemClickListener { popupMenuId ->
                    when (popupMenuId.itemId) {
                        R.id.cheap_to_expensive_item -> {
                            enumSortedType = EnumSorted.CHEAP_TO_EXPENSIVE
                        }
                        R.id.expensive_to_cheap_item -> {
                            enumSortedType = EnumSorted.EXPENSIVE_TO_CHEAP
                        }
                    }
                    sortedProductResponse.addAll(viewModel.sortedList(enumSortedType))
                    adapter?.notifyDataSetChanged()
                    recylerviewProducts.scrollToPosition(0)
                    return@setOnMenuItemClickListener true
                }
                popup.show()
            }

            swipeRefreshLayout.setOnRefreshListener(this@HomeFragment)
        }
    }

    //Get selected category from filter activity.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.ACTIVITY_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val tag = data?.getStringExtra(Constants.ACTIVITY_RESULT_TAG)
                binding.edittextSearch.setText(tag)
                if (!tag.equals(""))
                    binding.btnFilter.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_filter_badget,
                        0,
                        0,
                        0
                    )
                else
                    binding.btnFilter.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_filter_unbadget,
                        0,
                        0,
                        0
                    )
            }
        }
    }

    //call from refresh layout
    override fun onRefresh() {
        viewModel.getData()
    }
}