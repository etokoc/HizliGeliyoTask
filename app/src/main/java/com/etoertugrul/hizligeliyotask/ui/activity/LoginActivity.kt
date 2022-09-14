package com.etoertugrul.hizligeliyotask.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.etoertugrul.hizligeliyotask.R
import com.etoertugrul.hizligeliyotask.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewModel: LoginViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]
    }
}