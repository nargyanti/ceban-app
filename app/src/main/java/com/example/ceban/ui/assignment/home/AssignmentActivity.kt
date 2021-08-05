package com.example.ceban.ui.assignment.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.core.model.Assignment
import com.example.ceban.utils.AssignmentsData
import com.example.ceban.R

class AssignmentActivity : AppCompatActivity() {

    private lateinit var rvAssignments: RecyclerView
    private var list: ArrayList<Assignment> = arrayListOf()

    companion object {
        const val EXTRA_SUBJECTID = "extra_subjectid"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment)
        rvAssignments = findViewById(R.id.rv_assignment)
        rvAssignments.setHasFixedSize(true)

        val subjectId = intent.getIntExtra(EXTRA_SUBJECTID, 0)

        list.addAll(AssignmentsData.listData(subjectId))
        showRecyclerList(subjectId)

        supportActionBar?.title = "Daftar Tugas"
    }

    private fun showRecyclerList(subjectId: Int) {
        rvAssignments.layoutManager = LinearLayoutManager(this)
        val listAnimalAdapter = CardViewAssignmentAdapter(list)
        rvAssignments.adapter = listAnimalAdapter
    }
}