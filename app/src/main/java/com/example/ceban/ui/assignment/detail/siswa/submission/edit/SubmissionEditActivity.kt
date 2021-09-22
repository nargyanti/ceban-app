package com.example.ceban.ui.assignment.detail.siswa.submission.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ceban.core.datasource.remote.responses.StatusResponse
import com.example.ceban.databinding.ActivitySubmissionEditBinding
import com.example.ceban.databinding.AssignmentFileDialogBinding
import com.example.ceban.ui.assignment.detail.AssignmentDetailActivity
import com.example.ceban.ui.assignment.detail.siswa.AttachmentToAddAdapter
import com.example.ceban.utils.Attachment
import com.example.ceban.utils.ViewModelFactory
import java.io.File
import java.io.FileOutputStream

class SubmissionEditActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ASSIGNMENT_ID = "extra_assignment_id"
    }

    private lateinit var binding: ActivitySubmissionEditBinding
    private var fileListToAdd = ArrayList<Attachment>()
    private lateinit var attachmentToAddAdapter: AttachmentToAddAdapter
    private lateinit var viewModel: SubmissionEditViewModel
    private lateinit var adapter: SubmissionEditAdapter
    var answerId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubmissionEditBinding.inflate(layoutInflater)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[SubmissionEditViewModel::class.java]
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        answerId = intent.getIntExtra(EXTRA_ASSIGNMENT_ID, 0)

        if (answerId > 0) {
            adapter = object : SubmissionEditAdapter() {
                override fun onDeleteButtonClickCallback(id: Int) {
                    viewModel.deleteAnswerPictures(id).observe(this@SubmissionEditActivity) {
                        when(it.status) {
                            StatusResponse.SUCCESS -> {
                                getPictures()
                            }
                            StatusResponse.ERROR -> {
                                Log.e("DeletePicture", "Error: ${it.message}")
                            }
                        }
                    }
                }

            }

            getPictures()

            binding.rvAnswerEdit.adapter = adapter
            binding.rvAnswerEdit.layoutManager = LinearLayoutManager(this)
        }

        binding.btnAddAnotherFile.setOnClickListener {
            openAddFileDialog()
        }
    }

    private fun getPictures() {
        viewModel.getAnswerPictures(answerId).observe(this) {
            when(it.status) {
                StatusResponse.SUCCESS -> {
                    adapter.setData(it.body)
                }
                else -> {
                    Toast.makeText(this, "Terjadi kesalahan saat mengambil gambar jawaban", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openAddFileDialog() {
        val builder = AlertDialog.Builder(this)
        val addFileBinding: AssignmentFileDialogBinding =
            AssignmentFileDialogBinding.inflate(layoutInflater)
        builder.setView(addFileBinding.root)

        addFileBinding.btnAddFile.setOnClickListener {
            val chooseFile = Intent(Intent.ACTION_PICK)
            chooseFile.type = "image/*"
            val intent = Intent.createChooser(chooseFile, "Choose a File")
            startActivityForResult(intent, AssignmentDetailActivity.REQUEST_CODE)
        }

        addFileBinding.rvAssignment.layoutManager = LinearLayoutManager(this)
        attachmentToAddAdapter = AttachmentToAddAdapter()
        addFileBinding.rvAssignment.adapter = attachmentToAddAdapter
        attachmentToAddAdapter.setData(fileListToAdd)

        builder.setCancelable(true)
        val alertDialog = builder.create()
        addFileBinding.btnSubmit.setOnClickListener {
            viewModel.setFileList(fileListToAdd)
            viewModel.fileList.observe(this) { attachmentList ->
                attachmentList.forEach {
                    uploadPictures(it)
                }
                alertDialog.cancel()
            }
        }
        alertDialog.show()
    }

    private fun uploadPictures(attachment: Attachment) {
        viewModel.addPictures(attachment.file, answerId).observe(this) {
            when(it.status) {
                StatusResponse.SUCCESS -> {
                    getPictures()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AssignmentDetailActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                Log.d("GetFile", "Path : ${uri.path}")
                val inputStream = contentResolver.openInputStream(uri)
                val file = File(File(filesDir, "photos"), "3.jpg")
                if (file.exists()) file.delete() else file.parentFile.mkdirs()
                val outputStream = FileOutputStream(file)
                if (inputStream != null) {
                    val buf = ByteArray(8192)
                    var length: Int
                    while (inputStream.read(buf).also { length = it } > 0) {
                        outputStream.write(buf, 0, length)
                    }
                }

                Log.d("Get File", "File name: ${file.name}")

                val attachment = Attachment(file.name, file)
                fileListToAdd.add(attachment)
                attachmentToAddAdapter.setData(fileListToAdd)
            }
        }
    }
}