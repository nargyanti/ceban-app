package com.example.ceban.ui.assignment.detail.siswa.submission.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ceban.databinding.ItemStudentAnswerBinding
import com.example.ceban.utils.Attachment

class SubmissionAdapter: RecyclerView.Adapter<SubmissionAdapter.StudentAnswerViewHolder>() {
    val attachmentList = ArrayList<Attachment>()

    fun setData(data: List<Attachment>) {
        attachmentList.clear()
        attachmentList.addAll(data)
        notifyDataSetChanged()
    }

    class StudentAnswerViewHolder(private var binding: ItemStudentAnswerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(attachment: Attachment) {
            binding.tvFileName.text = attachment.namaFile
            Glide.with(binding.root).load(attachment.urlFile).into(binding.imgThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAnswerViewHolder {
        val binding = ItemStudentAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentAnswerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentAnswerViewHolder, position: Int) {
        holder.bind(attachmentList[position])
    }

    override fun getItemCount(): Int {
        return attachmentList.size
    }
}