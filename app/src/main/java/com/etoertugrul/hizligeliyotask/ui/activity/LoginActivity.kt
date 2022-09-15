package com.etoertugrul.hizligeliyotask.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.etoertugrul.hizligeliyotask.databinding.ActivityCategoryBinding
import com.etoertugrul.hizligeliyotask.databinding.ActivityLoginBinding
import com.etoertugrul.hizligeliyotask.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: LoginViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]
    }
}