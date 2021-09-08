package com.example.ceban.ui.assignment.detail.siswa.submission.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ceban.core.datasource.remote.responses.*
import com.example.ceban.core.model.Assignment
import com.example.ceban.databinding.ActivitySubmissionBinding
import com.example.ceban.ui.assignment.detail.siswa.submission.edit.SubmissionEditActivity
import com.example.ceban.utils.Attachment
import com.example.ceban.utils.ViewModelFactory

class SubmissionActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ASSIGNMENT = "extra_assignment"
        const val EXTRA_ANSWER = "extra_answer"
    }

    private lateinit var binding: ActivitySubmissionBinding
    private lateinit var viewModel: AnswerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmissionBinding.inflate(layoutInflater)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[AnswerViewModel::class.java]
        setContentView(binding.root)

        supportActionBar?.title = "Jawaban Siswa"
    }

    override fun onStart() {
        super.onStart()
        val assignment = intent.getParcelableExtra<AssignmentResponseItem>(EXTRA_ASSIGNMENT)
        val answer = intent.getParcelableExtra<AnswerResponse>(EXTRA_ANSWER)

        if (assignment != null) {
            binding.tvAssignmentDetailName.text = assignment.name
            binding.tvAssignmentDetailDuedatetime.text = assignment.dueDatetime
            binding.tvAssignmentDetailQuestion.text = assignment.question

            when {
                assignment.answerCount > 0 -> {
                    prepareView(assignment.answerCount)
                }
                answer != null -> {
                    prepareView(answer.id)
                }
            }
        }


    }

    fun prepareView(id: Int) {
        viewModel.getAnswerPictures(id).observe(this) {
            prepareRv(it)
        }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, SubmissionEditActivity::class.java)
            intent.putExtra(SubmissionEditActivity.EXTRA_ASSIGNMENT_ID, id)
            startActivity(intent)
        }
    }

    private fun prepareRv(data: ApiResponse<List<AnswerPictureResponse>>) {
        when(data.status) {
            StatusResponse.SUCCESS -> {
                val adapter = SubmissionAdapter()
                binding.rvAssignment.adapter = adapter
                binding.rvAssignment.layoutManager = LinearLayoutManager(this)

                adapter.setData(data.body)
            }
            else -> {

            }
        }
    }
}