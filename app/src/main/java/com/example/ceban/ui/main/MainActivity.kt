package com.example.ceban.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.R
import com.example.ceban.core.model.Subject
import com.example.ceban.utils.SubjectsData
import com.example.ceban.utils.ViewModelFactory
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var rvSubjects: RecyclerView
    private var list: ArrayList<Subject> = arrayListOf()
    private lateinit var viewModel: ClassesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ClassesViewModel::class.java]

        rvSubjects = findViewById(R.id.rv_subjects)
        rvSubjects.setHasFixedSize(true)

        list.addAll(SubjectsData.listData)
        showRecyclerCardView()

        supportActionBar?.title = "Mata Pelajaran"
    }

    private fun showRecyclerCardView() {
        viewModel.getAllClasses().observe(this, {
            rvSubjects.layoutManager = LinearLayoutManager(this)
            val cardViewSubjectAdapter = CardViewSubjectAdapter(list)
            rvSubjects.adapter = cardViewSubjectAdapter
        })
    }
}