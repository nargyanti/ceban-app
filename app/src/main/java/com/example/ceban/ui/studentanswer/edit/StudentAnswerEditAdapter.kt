package com.example.ceban.ui.studentanswer.edit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ceban.databinding.ItemStudentAnswerEditBinding
import com.example.ceban.utils.Attachment

class StudentAnswerEditAdapter: RecyclerView.Adapter<StudentAnswerEditAdapter.StudentViewHolder>() {
    val attachmentList = ArrayList<Attachment>()

    fun setData(data: List<Attachment>) {
        attachmentList.clear()
        attachmentList.addAll(data)
        notifyDataSetChanged()
    }

    class StudentViewHolder(private var binding: ItemStudentAnswerEditBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(attachment: Attachment) {
            binding.tvNamaFile.text = attachment.namaFile
            Glide.with(binding.root).load(attachment.urlFile).into(binding.imgEditThumbnail)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentAnswerEditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(attachmentList[position])
    }

    override fun getItemCount(): Int {
        return attachmentList.size
    }
}