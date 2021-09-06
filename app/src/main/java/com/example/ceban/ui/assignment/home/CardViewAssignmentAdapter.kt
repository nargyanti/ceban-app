package com.example.ceban.ui.assignment.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.R
import com.example.ceban.core.datasource.local.entity.UserEntity
import com.example.ceban.core.datasource.remote.responses.AssignmentResponseItem
import com.example.ceban.core.model.Assignment
import com.example.ceban.ui.assignment.detail.AssignmentDetailActivity
import com.example.ceban.ui.assignment.detail.siswa.submission.home.SubmissionActivity

class CardViewAssignmentAdapter(
    private val listAssignment: List<AssignmentResponseItem>,
    private val user: UserEntity
    ) :
    RecyclerView.Adapter<CardViewAssignmentAdapter.CardViewViewHolder>() {

    class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_assignment_name)
        var tvDueDateTime: TextView = itemView.findViewById(R.id.tv_assignment_duedatetime)
        var cardAssignment: CardView = itemView.findViewById(R.id.card_assignment)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_cardview_assignment, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val assignment = listAssignment[position]
        holder.tvName.text = assignment.name
        holder.tvDueDateTime.text = assignment.dueDatetime

        holder.cardAssignment.setOnClickListener {
//            TODO("cek apakah soal sudah dikerjakan atau belum. jika ya pindah ke halaman detail, jika belum pergi ke add jawaban")
            if(user.level == "Student") {
                if(assignment.answerCount == 0) {
                    val context = holder.cardAssignment.context
                    val moveToDetailIntent = Intent(context, AssignmentDetailActivity::class.java)
                    moveToDetailIntent.putExtra(AssignmentDetailActivity.EXTRA_ASSIGNMENT, assignment)
                    context.startActivity(moveToDetailIntent)
                }else{
                    val context = holder.cardAssignment.context
                    val moveToStudentAnswerIntent = Intent(context, SubmissionActivity::class.java)
                    moveToStudentAnswerIntent.putExtra(SubmissionActivity.EXTRA_ASSIGNMENT, assignment)
                    context.startActivity(moveToStudentAnswerIntent)
                }
            }else if(user.level == "Teacher") {
                val context = holder.cardAssignment.context
                val moveToDetailIntent = Intent(context, AssignmentDetailActivity::class.java)
                moveToDetailIntent.putExtra(AssignmentDetailActivity.EXTRA_ASSIGNMENT, assignment)
                context.startActivity(moveToDetailIntent)
            }
    }
    }

    override fun getItemCount(): Int {
        return listAssignment.size
    }
}