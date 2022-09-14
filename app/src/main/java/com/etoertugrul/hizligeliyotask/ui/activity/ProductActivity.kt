package com.etoertugrul.hizligeliyotask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.etoertugrul.hizligeliyotask.databinding.ActivityProductBinding
import com.etoertugrul.hizligeliyotask.ui.viewmodel.ProductViewModel

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel: ProductViewModel =
            ViewModelProvider(this)[ProductViewModel::class.java]

        //Setup navigation component
        binding.apply {
            val navController = (supportFragmentManager
                .findFragmentById(fragmentContainer.id) as NavHostFragment)
                .navController
            setupActionBarWithNavController(navController)
            bottomNavigationView.setupWithNavController(navController)
        }

    }
}