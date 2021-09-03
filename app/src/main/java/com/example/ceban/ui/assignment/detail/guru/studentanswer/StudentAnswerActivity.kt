package com.example.ceban.ui.assignment.detail.guru.studentanswer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ceban.R
import com.example.ceban.core.model.Assignment
import com.example.ceban.core.model.Student
import com.example.ceban.databinding.ActivityStudentAnswerBinding

class StudentAnswerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STUDENT = "extra_student"
        const val EXTRA_ASSIGNMENT = "extra_assignment"
    }

    private lateinit var binding: ActivityStudentAnswerBinding
    private var student: Student? = null
    private var assignment: Assignment? = null

    var imageList = arrayListOf("https://cdn.idntimes.com/content-images/post/20200810/4-b85fc68aa0c39ec0d7cf8292b429ec09_600x400.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        student = intent.getParcelableExtra(EXTRA_STUDENT)
        assignment = intent.getParcelableExtra(EXTRA_ASSIGNMENT)

        binding.tvStudentAnswer.text = resources.getString(R.string.student_answer)
        if (student != null) {
            student.let {
                binding.tvStudentName.text = it?.name
                binding.tvAbsen.text = "No. Absen: ${it?.no}"
            }

        }
        prepareImage()
    }

    private fun prepareImage() {
        val adapter = ImageAdapter(this, imageList)
        binding.vpStudentAnswer.adapter = adapter
    }
}