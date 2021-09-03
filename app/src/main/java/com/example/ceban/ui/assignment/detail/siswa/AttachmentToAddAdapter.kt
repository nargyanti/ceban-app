package com.example.ceban.ui.assignment.detail.siswa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.databinding.ItemAttachmentToAddBinding
import com.example.ceban.utils.Attachment

class AttachmentToAddAdapter: RecyclerView.Adapter<AttachmentToAddAdapter.AttachmentViewHolder>() {
    val attachmentList = ArrayList<Attachment>()

    fun setData(data: List<Attachment>) {
        attachmentList.clear()
        attachmentList.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: Attachment) {
        attachmentList.add(data)
        notifyItemInserted(attachmentList.size - 1)
    }

    class AttachmentViewHolder(private val binding: ItemAttachmentToAddBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(attachment: Attachment) {
            binding.tvFileToAddName.text = attachment.namaFile
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttachmentViewHolder {
        val binding = ItemAttachmentToAddBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttachmentViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AttachmentViewHolder,
        position: Int
    ) {
        holder.bind(attachmentList[position])
    }

    override fun getItemCount(): Int {
        return attachmentList.size
    }
}