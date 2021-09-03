package com.example.ceban.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.ceban.R
import com.example.ceban.core.datasource.local.entity.UserEntity
import com.example.ceban.core.datasource.remote.responses.ApiResponse
import com.example.ceban.core.datasource.remote.responses.StatusResponse
import com.example.ceban.databinding.ActivityLoginBinding
import com.example.ceban.ui.main.MainActivity
import com.example.ceban.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[LoginViewModel::class.java]
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text
            val password = binding.edtPassword.text

            viewModel.login(username = username.toString(), password = password.toString()).observe(this, {
                when(it.status) {
                    StatusResponse.SUCCESS -> {
                        val userEntity = UserEntity(
                            id = it.body.id,
                            name = it.body.name,
                            username = it.body.username,
                            password = it.body.password,
                            telp = it.body.telp,
                            level = it.body.level,
                            entryYear = it.body.entryYear
                        )
                        viewModel.saveUser(userEntity)
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    else -> {
                        Snackbar.make(binding.root, "Terjadi kesalahan saat login", Snackbar.LENGTH_SHORT).show()
                        Log.e("Login", "Error: ${it.message}")
                    }
                }
            })

        }
    }
}