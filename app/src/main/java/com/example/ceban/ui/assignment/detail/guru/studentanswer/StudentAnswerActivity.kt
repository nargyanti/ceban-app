package com.example.ceban.ui.assignment.detail.guru.studentanswer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ceban.core.model.Assignment
import com.example.ceban.core.model.Student
import com.example.ceban.databinding.ActivityStudentAnswerBinding
import com.synnapps.carouselview.CarouselView

const val EXTRA_STUDENT = "extra_student"
const val EXTRA_ASSIGNMENT = "extra_assignment"

class StudentAnswerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentAnswerBinding
    private var student: Student? = null
    private var assignment: Assignment? = null

    var imageList = arrayListOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        student = intent.getParcelableExtra(EXTRA_STUDENT)
        assignment = intent.getParcelableExtra(EXTRA_ASSIGNMENT)

        binding.crsStudentAnswer.pageCount = imageList.size
        binding.crsStudentAnswer.setImageListener { position, imageView ->
            Glide.with(this).load(imageList[position]).into(imageView)
        }
    }
}