package com.etoertugrul.hizligeliyotask.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.etoertugrul.hizligeliyotask.R
import com.etoertugrul.hizligeliyotask.databinding.ActivityLoginBinding
import com.etoertugrul.hizligeliyotask.ui.viewmodel.LoginViewModel
import com.etoertugrul.hizligeliyotask.util.show

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: LoginViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]
        initView()
    }

    private fun initView() {
        binding.apply {
            btnLogin.setOnClickListener {
                startActivity(Intent(this@LoginActivity, ProductActivity::class.java))
            }
            btnSignUp.setOnClickListener {
                getString(R.string.sign_up_text).show(applicationContext)
            }
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}