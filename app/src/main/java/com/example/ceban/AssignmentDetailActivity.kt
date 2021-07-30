package com.example.ceban

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AssignmentDetailActivity : AppCompatActivity() {

    private lateinit var rvStudentList: RecyclerView
    private var list: ArrayList<Student> = arrayListOf()

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DUEDATETIME = "extra_duedatetime"
        const val EXTRA_QUESTION = "extra_question"
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment_detail)

        val tvName: TextView = findViewById(R.id.tv_assignment_detail_name)
        val tvDueDateTime: TextView = findViewById(R.id.tv_assignment_detail_duedatetime)
        val tvQuestion: TextView = findViewById(R.id.tv_assignment_detail_question)
//        val btnUploadAnswer: Button = findViewById(R.id.btn_upload_answer)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val name = intent.getStringExtra(EXTRA_NAME)
        val dueDateTime = intent.getStringExtra(EXTRA_DUEDATETIME)
        val question = intent.getStringExtra(EXTRA_QUESTION)

        tvName.text = name
        tvDueDateTime.text = dueDateTime
        tvQuestion.text = question

//        rvStudentList = findViewById(R.id.rv_assignment)
//        rvStudentList.setHasFixedSize(true)

//        list.addAll(StudentsData.listData)

        supportActionBar?.title = "Detail Tugas"

//        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvStudentList.layoutManager = LinearLayoutManager(this)
        val listStudentAdapter = CardViewStudentListAdapter(list)
        rvStudentList.adapter = listStudentAdapter
    }
}