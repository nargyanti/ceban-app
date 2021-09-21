package com.example.ceban.ui.assignment.detail.siswa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.databinding.ItemFileBinding
import com.example.ceban.utils.Attachment

class FileListAdapter: RecyclerView.Adapter<FileListAdapter.FileListViewHolder>() {
    private val fileList = ArrayList<Attachment>()

    fun setData(data: List<Attachment>) {
        fileList.clear()
        fileList.addAll(data)
        notifyItemRangeChanged(0, data.size)
    }

    class FileListViewHolder(var binding: ItemFileBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(attachment: Attachment) {
            binding.textView.text = attachment.namaFile
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileListViewHolder {
        val binding = ItemFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FileListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileListViewHolder, position: Int) {
        holder.bind(fileList[position])
    }

    override fun getItemCount(): Int {
        return fileList.size
    }

}