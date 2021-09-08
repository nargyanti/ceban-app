package com.example.ceban.ui.assignment.detail.siswa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ceban.core.datasource.local.entity.UserEntity
import com.example.ceban.core.datasource.remote.requests.AnswerRequest
import com.example.ceban.core.datasource.remote.responses.AssignmentResponseItem
import com.example.ceban.core.datasource.remote.responses.StatusResponse
import com.example.ceban.core.model.Assignment
import com.example.ceban.databinding.AssignmentFileDialogBinding
import com.example.ceban.databinding.FragmentSiswaAssignmentDetailBinding
import com.example.ceban.ui.assignment.detail.AssignmentDetailActivity
import com.example.ceban.ui.assignment.detail.AssignmentDetailViewModel
import com.example.ceban.ui.assignment.detail.siswa.submission.home.SubmissionActivity
import com.example.ceban.utils.Attachment
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val EXTRA_ASSIGNMENT = "extra_assignment"
class SiswaAssignmentDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var assignment: AssignmentResponseItem? = null
    private lateinit var binding: FragmentSiswaAssignmentDetailBinding
    private var fileListToAdd = ArrayList<Attachment>()
    private lateinit var attachmentToAddAdapter: AttachmentToAddAdapter
    private lateinit var viewModel: AssignmentDetailViewModel
    private var user: UserEntity = UserEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            assignment = it.getParcelable(EXTRA_ASSIGNMENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSiswaAssignmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[AssignmentDetailViewModel::class.java]
            binding.rvAssignment.layoutManager = LinearLayoutManager(activity)
            val adapter = FileListAdapter()
            binding.rvAssignment.adapter = adapter
            viewModel.fileList.observe(viewLifecycleOwner, { attachmentList ->
                adapter.setData(attachmentList)
            })

            viewModel.getUser().observe(viewLifecycleOwner) {
                user = it
            }

            binding.btnSubmit.setOnClickListener {
                addAnswer()
            }

            binding.btnUploadAnswer.setOnClickListener {
                openAddFileDialog()
            }
        }
    }

    private fun addAnswer() {
        assignment.let { data ->
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val date = dateFormat.format(Date())
            val request = AnswerRequest(
                assignmentId = data?.id,
                score = 0,
                studentId = user.id,
                submitDatetime = date
            )
            viewModel.addAnswer(request).observe(viewLifecycleOwner) { answer ->
                when(answer.status) {
                    StatusResponse.SUCCESS -> {
                        val uploadStatus = arrayListOf<Boolean>()
                        viewModel.fileList.observe(viewLifecycleOwner) { fileList ->
                            fileList.forEach { attachment ->
                                viewModel.addAnswerPictures(attachment.file, answer.body.id)
                                    .observe(viewLifecycleOwner){ answerPicture ->
                                        when(answerPicture.status) {
                                            StatusResponse.SUCCESS -> {
                                                Toast.makeText(context, "Berhasil mengunggah gambar", Toast.LENGTH_SHORT).show()
                                                uploadStatus.add(true)
                                            }
                                            else -> {
                                                Toast.makeText(context, "Terjadi kesalahan saat mengunggah gambar", Toast.LENGTH_SHORT).show()
                                                uploadStatus.add(false)
                                            }
                                        }
                                    }
                            }
                            if (uploadStatus.size == fileListToAdd.size && uploadStatus.all { it }) {
                                startActivity(Intent(activity, SubmissionActivity::class.java).apply {
                                    putExtra(SubmissionActivity.EXTRA_ASSIGNMENT, assignment)
                                    putExtra(SubmissionActivity.EXTRA_ANSWER, answer.body)
                                })
                                activity?.finish()
                            }
                        }

                    }
                    else -> {
                        Toast.makeText(context, "Terjadi kesalahan saat menambah jawaban", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun openAddFileDialog() {
        val builder = AlertDialog.Builder(requireActivity())
        val addFileBinding: AssignmentFileDialogBinding =
            AssignmentFileDialogBinding.inflate(layoutInflater)
        builder.setView(addFileBinding.root)

        addFileBinding.btnAddFile.setOnClickListener {
            val chooseFile = Intent(Intent.ACTION_PICK)
            chooseFile.type = "image/*"
            val intent = Intent.createChooser(chooseFile, "Choose a File")
            startActivityForResult(intent, AssignmentDetailActivity.REQUEST_CODE)
        }

        addFileBinding.rvAssignment.layoutManager = LinearLayoutManager(context)
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
        if (requestCode == AssignmentDetailActivity.REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                Log.d("GetFile", "Path : ${uri.path}")
                var inputStream =  activity?.contentResolver?.openInputStream(uri)
                val date = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
                var file = File(File(activity?.filesDir, "photos"), "$date.jpg")
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

    companion object {
        @JvmStatic
        fun newInstance(param1: AssignmentResponseItem) =
            SiswaAssignmentDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_ASSIGNMENT, param1)
                }
            }
    }
}