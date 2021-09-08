package com.example.ceban.ui.assignment.detail.siswa.submission.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ceban.core.datasource.remote.responses.AnswerPictureResponse
import com.example.ceban.databinding.ItemStudentAnswerEditBinding
import com.example.ceban.utils.Attachment
import com.example.ceban.utils.Injection

abstract class SubmissionEditAdapter : RecyclerView.Adapter<SubmissionEditAdapter.StudentViewHolder>() {
    val attachmentList = ArrayList<AnswerPictureResponse>()

    fun setData(data: List<AnswerPictureResponse>) {
        attachmentList.clear()
        attachmentList.addAll(data)
        notifyDataSetChanged()
    }

    inner class StudentViewHolder(private var binding: ItemStudentAnswerEditBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(attachment: AnswerPictureResponse) {
            binding.tvNamaFile.text = attachment.path
            Glide
                .with(binding.root)
                .load(Injection.HOST + "/download?filename=" + attachment.path)
                .into(binding.imgEditThumbnail)
            binding.imageView4.setOnClickListener {
                onDeleteButtonClickCallback(attachment.id)
            }
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

    abstract fun onDeleteButtonClickCallback(id: Int)
}