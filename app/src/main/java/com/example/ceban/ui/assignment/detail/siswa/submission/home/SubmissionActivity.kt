package com.example.ceban.ui.assignment.detail.siswa.submission.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ceban.core.model.Assignment
import com.example.ceban.databinding.ActivitySubmissionBinding
import com.example.ceban.ui.assignment.detail.siswa.submission.edit.SubmissionEditActivity
import com.example.ceban.utils.Attachment

class SubmissionActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ASSIGNMENT = "extra_assignment"
    }

    private lateinit var binding: ActivitySubmissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Jawaban Siswa"
    }

    override fun onStart() {
        super.onStart()
        val assignment = intent.getParcelableExtra<Assignment>(EXTRA_ASSIGNMENT)

        if (assignment != null) {
            binding.tvAssignmentDetailName.text = assignment.name
            binding.tvAssignmentDetailDuedatetime.text = assignment.dueDateTime
            binding.tvAssignmentDetailQuestion.text = assignment.question
        }

        val adapter = SubmissionAdapter()
        binding.rvAssignment.adapter = adapter
        binding.rvAssignment.layoutManager = LinearLayoutManager(this)

        val attachment = listOf(
            Attachment("Jawaban 2", null, "https://placeimg.com/640/480/any"),
            Attachment("Jawaban 2", null, "https://placeimg.com/640/480/any"),
        )
        adapter.setData(attachment)

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, SubmissionEditActivity::class.java)
            intent.putExtra(SubmissionEditActivity.EXTRA_ASSIGNMENT, assignment)
        }


    }
}