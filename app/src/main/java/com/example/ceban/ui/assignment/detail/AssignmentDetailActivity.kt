package com.example.ceban.ui.assignment.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ceban.core.model.Assignment
import com.example.ceban.databinding.ActivityAssignmentDetailBinding
import com.example.ceban.databinding.AssignmentFileDialogBinding
import com.example.ceban.ui.studentanswer.home.StudentAnswerActivity
import com.example.ceban.utils.Attachment
import java.io.File
import java.io.FileOutputStream

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