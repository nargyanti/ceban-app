package com.example.ceban.ui.assignment.detail.guru

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.R
import com.example.ceban.core.model.Student
import com.example.ceban.databinding.ItemCardviewStudentListBinding
import kotlin.random.Random

class CardViewStudentListAdapter(private val listStudent: ArrayList<Student>) :
    RecyclerView.Adapter<CardViewStudentListAdapter.CardViewViewHolder>() {

    class CardViewViewHolder(private val binding: ItemCardviewStudentListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.tvAssignmentStudentName.text = student.name
            val nilai = Random.nextInt(16, 20) * 5
            binding.tvNilai.text = "Nilai : ${nilai}"

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