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
import com.example.ceban.core.model.Student
import com.example.ceban.databinding.ActivityAssignmentDetailBinding
import com.example.ceban.databinding.AssignmentFileDialogBinding
import java.io.File
import java.io.FileOutputStream

class AssignmentDetailActivity : AppCompatActivity() {

    private lateinit var rvStudentList: RecyclerView
    private var list: ArrayList<Student> = arrayListOf()

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DUEDATETIME = "extra_duedatetime"
        const val EXTRA_QUESTION = "extra_question"
        const val EXTRA_NAME = "extra_name"
        const val REQUEST_CODE = 1
    }

    private lateinit var binding: ActivityAssignmentDetailBinding
    private var fileList = ArrayList<File>()
    private lateinit var viewModel: AssignmentDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssignmentDetailBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[AssignmentDetailViewModel::class.java]
        setContentView(binding.root)
        supportActionBar?.title = "Detail Tugas"
    }

    override fun onStart() {
        super.onStart()
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val name = intent.getStringExtra(EXTRA_NAME)
        val dueDateTime = intent.getStringExtra(EXTRA_DUEDATETIME)
        val question = intent.getStringExtra(EXTRA_QUESTION)

        binding.tvAssignmentDetailName.text = name
        binding.tvAssignmentDetailDuedatetime.text = dueDateTime
        binding.tvAssignmentDetailQuestion.text = question

        binding.rvAssignment.layoutManager = LinearLayoutManager(this)
        val adapter = FileListAdapter()
        binding.rvAssignment.adapter = adapter
        viewModel.fileList.observe(this, {
            adapter.setData(it)
        })


        binding.btnUploadAnswer.setOnClickListener {
            openAddFileDialog()
        }
    }

    private fun openAddFileDialog() {
        val builder = AlertDialog.Builder(this)
        val addFileBinding: AssignmentFileDialogBinding = AssignmentFileDialogBinding.inflate(layoutInflater)
        builder.setView(addFileBinding.root)

        addFileBinding.btnAddFile.setOnClickListener {
            val chooseFile = Intent(Intent.ACTION_PICK)
            chooseFile.type = "image/*"
            val intent = Intent.createChooser(chooseFile, "Choose a File")
            startActivityForResult(intent, REQUEST_CODE)
        }
        addFileBinding.btnSubmit.setOnClickListener {  }

        builder.setCancelable(true)
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                Log.d("GetFile", "Path : ${uri.path}")
                var inputStream = contentResolver.openInputStream(uri)
                var file = File("3.jpg")
                var outputStream = FileOutputStream(file)
                if (inputStream != null) {
//                    FileUtils.copy(inputStream, outputStream)
                }
//                val metaCursor = contentResolver.query(uri, arrayOf( MediaStore.Images.Media.DATA ), null, null, null)?.use { cursor ->
//                    if (!cursor.moveToFirst()) return@use null
//
//                    val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//                    val s = cursor.getString(columnIndex)
//
//                    Log.d("GetFile", "Name : $s")
//                }
            }

        }
    }



















}