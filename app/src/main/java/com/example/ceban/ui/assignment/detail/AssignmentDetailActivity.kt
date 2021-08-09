package com.example.ceban.ui.assignment.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.ceban.core.model.Assignment
import com.example.ceban.databinding.ActivityAssignmentDetailBinding

class AssignmentDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ASSIGNMENT = "extra_assignment"
        const val REQUEST_CODE = 1
    }

    private lateinit var binding: ActivityAssignmentDetailBinding
    private lateinit var viewModel: AssignmentDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentDetailBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[AssignmentDetailViewModel::class.java]
        setContentView(binding.root)
        supportActionBar?.title = "Detail Tugas"
    }

    override fun onStart() {
        super.onStart()
        val assignment = intent.getParcelableExtra<Assignment>(EXTRA_ASSIGNMENT)
        if(assignment != null) {
            binding.tvAssignmentDetailName.text = assignment.name
            binding.tvAssignmentDetailDuedatetime.text = assignment.dueDateTime
            binding.tvAssignmentDetailQuestion.text = assignment.question
        }
    }


}