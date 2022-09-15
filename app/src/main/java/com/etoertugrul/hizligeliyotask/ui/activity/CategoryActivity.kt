package com.etoertugrul.hizligeliyotask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.etoertugrul.hizligeliyotask.databinding.ActivityCategoryBinding
import com.etoertugrul.hizligeliyotask.ui.viewmodel.CategoryViewModel

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: CategoryViewModel =
            ViewModelProvider(this)[CategoryViewModel::class.java]
    }
}