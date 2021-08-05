package com.example.ceban.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.R
import com.example.ceban.core.model.Subject
import com.example.ceban.utils.SubjectsData
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var rvSubjects: RecyclerView
    private var list: ArrayList<Subject> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvSubjects = findViewById(R.id.rv_subjects)
        rvSubjects.setHasFixedSize(true)

        list.addAll(SubjectsData.listData)
        showRecyclerCardView()

        supportActionBar?.title = "Mata Pelajaran"
    }

    private fun showRecyclerCardView() {
        rvSubjects.layoutManager = LinearLayoutManager(this)
        val cardViewSubjectAdapter = CardViewSubjectAdapter(list)
        rvSubjects.adapter = cardViewSubjectAdapter
    }
}