package com.example.ceban.ui.assignment.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.ceban.R
import com.example.ceban.core.datasource.remote.responses.AssignmentResponseItem
import com.example.ceban.core.model.Assignment
import com.example.ceban.databinding.ActivityAssignmentDetailBinding
import com.example.ceban.ui.assignment.detail.guru.GuruAssignmentDetailFragment
import com.example.ceban.ui.assignment.detail.siswa.SiswaAssignmentDetailFragment
import com.example.ceban.utils.ViewModelFactory

class AssignmentDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ASSIGNMENT = "extra_assignment"
        const val REQUEST_CODE = 1
    }

    private lateinit var binding: ActivityAssignmentDetailBinding
    private lateinit var viewModel: AssignmentDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentDetailBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[AssignmentDetailViewModel::class.java]
        setContentView(binding.root)
        supportActionBar?.title = "Detail Tugas"
    }

    override fun onStart() {
        super.onStart()
        val assignment = intent.getParcelableExtra<AssignmentResponseItem>(EXTRA_ASSIGNMENT)
        if(assignment != null) {
            binding.tvAssignmentDetailName.text = assignment.name
            binding.tvAssignmentDetailDuedatetime.text = assignment.dueDatetime
            binding.tvAssignmentDetailQuestion.text = assignment.question

            viewModel.getUser().observe(this) {
                Log.d("User", "Level: ${it.level}")
                if (it.level == "Teacher") {
                    val fragment = GuruAssignmentDetailFragment.newInstance(assignment)
                    supportFragmentManager.beginTransaction()
                        .add(R.id.assignment_detail_container, fragment)
                        .commit()

                }else{
                    val fragment = SiswaAssignmentDetailFragment.newInstance(assignment)
                    supportFragmentManager.beginTransaction()
                        .add(R.id.assignment_detail_container, fragment)
                        .commit()
                }
            }
        }
    }


}