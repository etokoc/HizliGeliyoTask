package com.etoertugrul.hizligeliyotask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.etoertugrul.hizligeliyotask.R
import com.etoertugrul.hizligeliyotask.ui.viewmodel.LoginViewModel
import com.etoertugrul.hizligeliyotask.ui.viewmodel.ProductViewModel

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val viewModel: ProductViewModel =
            ViewModelProvider(this)[ProductViewModel::class.java]
    }
}