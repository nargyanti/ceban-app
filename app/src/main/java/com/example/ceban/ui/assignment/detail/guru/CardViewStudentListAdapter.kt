package com.example.ceban.ui.assignment.detail.guru

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.R
import com.example.ceban.core.model.Student

class CardViewStudentListAdapter(private val listStudent: ArrayList<Student>) :
    RecyclerView.Adapter<CardViewStudentListAdapter.CardViewViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_assignment_student_name)
        var cardStudent: CardView = itemView.findViewById(R.id.card_student_list)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_cardview_student_list, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val student = listStudent[position]
        holder.tvName.text = student.name
//        holder.cardStudent.setOnClickListener {
//            val context = holder.cardStudent.context
//            val moveToDetailIntent = Intent(context, StudentAnswerActivity::class.java)
//            moveToDetailIntent.putExtra(StudentAnswerActivity.EXTRA_ID, student.id)
//            moveToDetailIntent.putExtra(StudentAnswerActivity.EXTRA_NAME, student.name)
//            context.startActivity(moveToDetailIntent)
//        }
    }

    override fun getItemCount(): Int {
        return listStudent.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Student)
    }

}