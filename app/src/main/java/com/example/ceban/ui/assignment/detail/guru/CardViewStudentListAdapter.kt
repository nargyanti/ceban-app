package com.example.ceban.ui.assignment.detail.guru

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.R
import com.example.ceban.core.datasource.remote.responses.AssignmentResponseItem
import com.example.ceban.core.datasource.remote.responses.AssignmentStudentResponse
import com.example.ceban.core.model.Assignment
import com.example.ceban.core.model.Student
import com.example.ceban.databinding.ItemCardviewStudentListBinding
import com.example.ceban.ui.assignment.detail.guru.studentanswer.StudentAnswerActivity
import kotlin.random.Random

class CardViewStudentListAdapter(private val assignment: AssignmentResponseItem?, private val listStudent: List<AssignmentStudentResponse>, private val context: Context) :
    RecyclerView.Adapter<CardViewStudentListAdapter.CardViewViewHolder>() {

    inner class CardViewViewHolder(private val binding: ItemCardviewStudentListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: AssignmentStudentResponse) {
            binding.tvAssignmentStudentName.text = student.name
            val nilai = student.score
            val id = student.answerId
            Log.d("Nilai", "$id : $nilai")
            binding.tvNilai.text = "Nilai : ${student.score}"

            binding.btnCheckStudentAnswer.setOnClickListener {
                val intent = Intent(context , StudentAnswerActivity::class.java)
                intent.putExtra(StudentAnswerActivity.EXTRA_ASSIGNMENT, assignment)
                intent.putExtra(StudentAnswerActivity.EXTRA_STUDENT, student)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): CardViewViewHolder {
        val binding = ItemCardviewStudentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listStudent[position])
    }

    override fun getItemCount(): Int {
        return listStudent.size
    }


}