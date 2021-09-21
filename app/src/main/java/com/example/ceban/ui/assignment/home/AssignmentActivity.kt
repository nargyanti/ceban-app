package com.example.ceban.ui.assignment.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ceban.core.datasource.remote.responses.StatusResponse
import com.example.ceban.databinding.ActivityAssignmentBinding
import com.example.ceban.ui.assignment.add.AssignmentAddActivity
import com.example.ceban.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class AssignmentActivity : AppCompatActivity() {

    private lateinit var viewModel: AssignmentViewModel
    private lateinit var binding: ActivityAssignmentBinding
    private var subjectId = 0

    companion object {
        const val EXTRA_SUBJECTID = "extra_subjectid"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelFactory.getInstance(this)
        viewModel  =ViewModelProvider(this, factory)[AssignmentViewModel::class.java]

        subjectId = intent.getIntExtra(EXTRA_SUBJECTID, 0)
        showRecyclerList()

        supportActionBar?.title = "Daftar Tugas"
    }

    private fun showRecyclerList() {
        viewModel.getUser().observe(this) {user ->
            viewModel.getAssignment(subjectId, user.level, user.id).observe(this) {
                when(it.status) {
                    StatusResponse.SUCCESS -> {
                        binding.rvAssignment.layoutManager = LinearLayoutManager(this)
                        val listAnimalAdapter = CardViewAssignmentAdapter(it.body, user)
                        binding.rvAssignment.adapter = listAnimalAdapter
                    }
                    else -> {
                        Snackbar.make(binding.root, "Terjadi kesalahan", Snackbar.LENGTH_SHORT).show()
                        Log.e("Login", "Error: ${it.message}")
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AssignmentAddActivity::class.java)
            intent.putExtra(AssignmentAddActivity.EXTRA_SUBJECT, subjectId)
            startActivity(intent)
        }
    }
}