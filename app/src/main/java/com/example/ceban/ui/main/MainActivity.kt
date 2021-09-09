package com.example.ceban.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.R
import com.example.ceban.core.datasource.remote.responses.StatusResponse
import com.example.ceban.core.model.Subject
import com.example.ceban.databinding.ActivityMainBinding
import com.example.ceban.ui.login.LoginActivity
import com.example.ceban.utils.SubjectsData
import com.example.ceban.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var rvSubjects: RecyclerView
    private lateinit var viewModel: ClassesViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ClassesViewModel::class.java]

        rvSubjects = findViewById(R.id.rv_subjects)
        rvSubjects.setHasFixedSize(true)

        showRecyclerCardView()

        supportActionBar?.title = "Mata Pelajaran"
    }

    private fun showRecyclerCardView() {
        viewModel.getUser().observe(this, { user ->
            viewModel.getAllClasses(user.id, user.level).observe(this, {
                when(it.status) {
                    StatusResponse.SUCCESS -> {
                        val list = it.body
                        rvSubjects.layoutManager = LinearLayoutManager(this)
                        val cardViewSubjectAdapter = CardViewSubjectAdapter(list)
                        rvSubjects.adapter = cardViewSubjectAdapter
                    }
                    else -> {
                        Snackbar.make(binding.root, "Terjadi kesalahan", Snackbar.LENGTH_SHORT).show()
                        Log.e("Login", "Error: ${it.message}")
                    }
                }
            })
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout -> {
                viewModel.logout()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        return true
    }
}