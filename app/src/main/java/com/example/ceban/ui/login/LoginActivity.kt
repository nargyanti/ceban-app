package com.example.ceban.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ceban.R
import com.example.ceban.databinding.ActivityLoginBinding
import com.example.ceban.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text
            val password = binding.edtPassword.text

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}