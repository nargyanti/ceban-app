package com.example.ceban.ui.assignment.detail.guru.studentanswer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ceban.R
import com.example.ceban.core.datasource.remote.responses.AssignmentResponseItem
import com.example.ceban.core.datasource.remote.responses.AssignmentStudentResponse
import com.example.ceban.core.model.Assignment
import com.example.ceban.core.model.Student
import com.example.ceban.databinding.ActivityStudentAnswerBinding
import com.example.ceban.ui.assignment.detail.guru.GuruAssignmentDetailFragment
import com.example.ceban.ui.assignment.detail.guru.studentanswer.answer.AnswerFragment
import com.example.ceban.ui.assignment.detail.guru.studentanswer.form.TeacherFormFragment

class StudentAnswerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STUDENT = "extra_student"
        const val EXTRA_ASSIGNMENT = "extra_assignment"
    }

    private lateinit var binding: ActivityStudentAnswerBinding
    private var student: AssignmentStudentResponse? = null
    private var assignment: AssignmentResponseItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        student = intent.getParcelableExtra(EXTRA_STUDENT)
        assignment = intent.getParcelableExtra(EXTRA_ASSIGNMENT)

        student.let {
            binding.tvStudentName.text = it?.name
            binding.tvAbsen.text = "No. Absen: ${it?.noAbsen}"

            if (it?.answerId == null) {
                val fragment = TeacherFormFragment.newInstance(student, assignment)
                supportFragmentManager.beginTransaction()
                    .add(R.id.student_answer_container, fragment)
                    .commit()
            }else{
                val fragment = AnswerFragment.newInstance(student, assignment)
                supportFragmentManager.beginTransaction()
                    .add(R.id.student_answer_container, fragment)
                    .commit()
            }
        }
    }

}