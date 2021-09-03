package com.example.ceban.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.R
import com.example.ceban.core.datasource.remote.responses.StatusResponse
import com.example.ceban.core.model.Subject
import com.example.ceban.databinding.ActivityMainBinding
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
            user.id?.let {
                viewModel.getAllClasses(it).observe(this, {
                    when(it.status) {
                        StatusResponse.SUCCESS -> {
                            val list = it.body.subjectsResponse
                            if (list != null) {
                                rvSubjects.layoutManager = LinearLayoutManager(this)
                                val cardViewSubjectAdapter = CardViewSubjectAdapter(list)
                                rvSubjects.adapter = cardViewSubjectAdapter
                            }
                        }
                        else -> {
                            Snackbar.make(binding.root, "Terjadi kesalahan", Snackbar.LENGTH_SHORT).show()
                            Log.e("Login", "Error: ${it.message}")
                        }
                    }
                })
            }
        })
    }
}