package com.example.ceban.ui.assignment.detail.siswa.submission.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ceban.core.datasource.remote.responses.AnswerPictureResponse
import com.example.ceban.core.datasource.remote.responses.AnswerResponse
import com.example.ceban.databinding.ItemStudentAnswerBinding
import com.example.ceban.utils.Attachment
import com.example.ceban.utils.Injection

class SubmissionAdapter: RecyclerView.Adapter<SubmissionAdapter.StudentAnswerViewHolder>() {
    private val attachmentList = ArrayList<AnswerPictureResponse>()

    fun setData(data: List<AnswerPictureResponse>) {
        attachmentList.clear()
        attachmentList.addAll(data)
        notifyItemRangeChanged(0, data.size)
    }

    class StudentAnswerViewHolder(private var binding: ItemStudentAnswerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(attachment: AnswerPictureResponse) {
            binding.tvFileName.text = attachment.path
            Glide.with(binding.root).load(Injection.HOST + "/download?filename=" + attachment.path).into(binding.imgThumbnail)
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