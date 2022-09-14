package com.etoertugrul.hizligeliyotask.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.etoertugrul.hizligeliyotask.R
import com.etoertugrul.hizligeliyotask.ui.viewmodel.CategoryViewModel
import com.etoertugrul.hizligeliyotask.ui.viewmodel.LoginViewModel

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val viewModel: CategoryViewModel =
            ViewModelProvider(this)[CategoryViewModel::class.java]
    }
}