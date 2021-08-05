package com.example.ceban.ui.assignment.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ceban.R
import com.example.ceban.core.model.Assignment
import com.example.ceban.core.model.Student
import com.example.ceban.databinding.ActivityAssignmentDetailBinding
import com.example.ceban.databinding.AssignmentFileDialogBinding
import com.example.ceban.ui.studentanswer.StudentAnswerActivity
import com.example.ceban.utils.Attachment
import java.io.File
import java.io.FileOutputStream

class AssignmentDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ASSIGNMENT = "extra_assignment"
        const val REQUEST_CODE = 1
    }

    private lateinit var binding: ActivityAssignmentDetailBinding
    private var fileListToAdd = ArrayList<Attachment>()
    private lateinit var viewModel: AssignmentDetailViewModel
    private lateinit var attachmentToAddAdapter: AttachmentToAddAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentDetailBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[AssignmentDetailViewModel::class.java]
        setContentView(binding.root)
        supportActionBar?.title = "Detail Tugas"
    }

    override fun onStart() {
        super.onStart()
        val assignment = intent.getParcelableExtra<Assignment>(EXTRA_ASSIGNMENT)
        if(assignment != null) {
            binding.tvAssignmentDetailName.text = assignment.name
            binding.tvAssignmentDetailDuedatetime.text = assignment.dueDateTime
            binding.tvAssignmentDetailQuestion.text = assignment.question
        }

        binding.rvAssignment.layoutManager = LinearLayoutManager(this)
        val adapter = FileListAdapter()
        binding.rvAssignment.adapter = adapter
        viewModel.fileList.observe(this, {
            adapter.setData(it)
        })

        binding.btnSubmit.setOnClickListener {
            startActivity(Intent(this, StudentAnswerActivity::class.java).apply {
                putExtra(StudentAnswerActivity.EXTRA_ASSIGNMENT, assignment)
            })
            finish()
        }

        binding.btnUploadAnswer.setOnClickListener {
            openAddFileDialog()
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
            startActivityForResult(intent, REQUEST_CODE)
        }

        addFileBinding.rvAssignment.layoutManager = LinearLayoutManager(this)
        attachmentToAddAdapter = AttachmentToAddAdapter()
        addFileBinding.rvAssignment.adapter = attachmentToAddAdapter
        attachmentToAddAdapter.setData(fileListToAdd)

        builder.setCancelable(true)
        val alertDialog = builder.create()
        addFileBinding.btnSubmit.setOnClickListener {
            viewModel.setFileList(fileListToAdd)
            alertDialog.cancel()
        }
        alertDialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                Log.d("GetFile", "Path : ${uri.path}")
                var inputStream = contentResolver.openInputStream(uri)
                var file = File(File(filesDir, "photos"), "3.jpg")
                if (file.exists()) file.delete() else file.parentFile.mkdirs()
                var outputStream = FileOutputStream(file)
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