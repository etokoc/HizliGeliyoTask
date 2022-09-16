package com.etoertugrul.hizligeliyotask.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.etoertugrul.hizligeliyotask.adapters.FilterRecylerViewAdapter
import com.etoertugrul.hizligeliyotask.databinding.ActivityFilterBinding
import com.etoertugrul.hizligeliyotask.ui.viewmodel.FilterViewModel
import com.etoertugrul.hizligeliyotask.util.Constants


class FilterActivity : AppCompatActivity(), FilterRecylerViewAdapter.OnItemClickListener {
    private lateinit var binding: ActivityFilterBinding
    var selectedCategory = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: FilterViewModel =
            ViewModelProvider(this)[FilterViewModel::class.java]
        initView()
    }

    private fun initView() {
        binding.apply {
            val manager = LinearLayoutManager(this@FilterActivity)
            recylerviewCategories.adapter =
                intent.getStringArrayListExtra(Constants.INTENT_TAG)?.let {
                    FilterRecylerViewAdapter(
                        this@FilterActivity,
                        it, this@FilterActivity
                    )
                }
            recylerviewCategories.layoutManager = manager
            btnClose.setOnClickListener {
                finish()
            }

            btnFilter.setOnClickListener {
                val intent = Intent()
                intent.putExtra(
                    Constants.ACTIVITY_RESULT_TAG, selectedCategory
                )
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    override fun onItemClicked(string: String) {
        selectedCategory = string
    }


}