package com.example.ceban

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class StudentAnswerActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_answer)

        val tvName: TextView = findViewById(R.id.tv_student_answer_name)
        val tvNo: TextView = findViewById(R.id.tv_student_answer_no)
        val tvResult: TextView = findViewById(R.id.tv_student_answer_result)

        val id = intent.getIntExtra(AssignmentDetailActivity.EXTRA_ID, 0)
        val name = intent.getStringExtra(AssignmentDetailActivity.EXTRA_NAME)

        tvName.text = name

        supportActionBar?.title = "Jawaban Siswa"
    }
}