package com.example.ceban.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.R
import com.example.ceban.core.datasource.remote.responses.SubjectsResponseItem
import com.example.ceban.ui.assignment.home.AssignmentActivity

class CardViewSubjectAdapter(private val listSubject: List<SubjectsResponseItem>) :
    RecyclerView.Adapter<CardViewSubjectAdapter.CardViewViewHolder>() {

    class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_subject_name)
        var tvClass: TextView = itemView.findViewById(R.id.tv_subject_class)
        var tvTeacher: TextView = itemView.findViewById(R.id.tv_subject_teacher)
        var tvAssignmentAmount: TextView = itemView.findViewById(R.id.tv_subject_assignmentAmount)
        var tvStudentAmount: TextView = itemView.findViewById(R.id.tv_subject_studentAmount)
        var cardSubject: CardView = itemView.findViewById(R.id.card_subject)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_cardview_subject, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val subject = listSubject[position]
        holder.tvName.text = subject.name
        holder.tvAssignmentAmount.text = "${subject.assignmentCount.toString()} Tugas"
        holder.tvTeacher.text = subject.teacherName
        holder.tvStudentAmount.text = "${subject.studentCount.toString()} Siswa"
        holder.cardSubject.setOnClickListener {
            val context = holder.cardSubject.context
            val moveToDetailIntent = Intent(context, AssignmentActivity::class.java)
            moveToDetailIntent.putExtra(AssignmentActivity.EXTRA_SUBJECTID, subject.id)
            context.startActivity(moveToDetailIntent)
        }
    }

    override fun getItemCount(): Int {
        return listSubject.size
    }

}