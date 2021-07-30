package com.example.ceban

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CardViewAssignmentAdapter(
    private val listAssignment: ArrayList<Assignment>) :
    RecyclerView.Adapter<CardViewAssignmentAdapter.CardViewViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDueDateTime: TextView = itemView.findViewById(R.id.tv_assignment_duedatetime)
        var tvName: TextView = itemView.findViewById(R.id.tv_assignment_name)
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
        holder.tvDueDateTime.text = assignment.dueDateTime

        holder.cardAssignment.setOnClickListener {
            val context = holder.cardAssignment.context
            val moveToDetailIntent = Intent(context, AssignmentDetailActivity::class.java)
            moveToDetailIntent.putExtra(AssignmentDetailActivity.EXTRA_ID, assignment.id)
            moveToDetailIntent.putExtra(AssignmentDetailActivity.EXTRA_NAME, assignment.name)
            moveToDetailIntent.putExtra(AssignmentDetailActivity.EXTRA_QUESTION, assignment.question)
            moveToDetailIntent.putExtra(AssignmentDetailActivity.EXTRA_DUEDATETIME, assignment.dueDateTime)
            context.startActivity(moveToDetailIntent)
        }
    }

    override fun getItemCount(): Int {
        return listAssignment.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Assignment)
    }
}